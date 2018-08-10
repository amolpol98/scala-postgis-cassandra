package com.assignment.routes

import java.util.UUID

import akka.event.LoggingAdapter
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.assignment.db.PostGISImpl
import io.circe.generic.auto._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import com.assignment.services.PostGISService
import com.assignment.dtos.http.request.{PostGISRequest, UpdateToDoRequest}

import scala.util.{Failure, Success}

trait Routes {
  val log: LoggingAdapter
  val postGISService: PostGISService

  val routes: Route = {
    path("test") {
      get {
        complete("test")
      }
    } ~ pathPrefix("postgis") {
      post {
        println("entered here")
        entity(as[PostGISRequest]) { createPostGIS =>
          onComplete(postGISService.create(createPostGIS)) {
            case Success(postGISItem) =>
              complete(Created, postGISItem.toDomainModel)
            case Failure(error) =>
              log.error(error.getMessage, "Failed to create To-Do item")
              complete(InternalServerError, "Internal Server Error")
          }
        }
      } ~ path(JavaUUID) { id: UUID =>
        get {
          onComplete(postGISService.retrieve(id)) {
            case Success(Some(postGISItem)) =>
              complete(OK, postGISItem.toDomainModel)
            case Success(None) =>
              complete(NotFound, "To-Do Item does not exist")
            case Failure(error) =>
              log.error(error.getMessage, "Failed to retrieve To-Do item")
              complete(InternalServerError, "Internal Server Error")
          }
        } ~ put {
          entity(as[PostGISRequest]) { req =>
            val updateToDo = UpdateToDoRequest(id, req.imageRef, req.pointType)
            onComplete(postGISService.update(updateToDo)) {
              case Success(toDoItem) =>
                complete(OK, toDoItem.toDomainModel)
              case Failure(error) =>
                log.error(error.getMessage, "Failed to update To-Do item")
                complete(InternalServerError, "Internal Server Error")
            }
          }
        }
      }
    }
  }
}
