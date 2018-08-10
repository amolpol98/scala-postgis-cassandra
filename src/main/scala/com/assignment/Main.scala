package com.assignment

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.Http
import com.assignment.db.AppDatabase
import com.assignment.db.PostGISImpl
import com.assignment.services.PostGISService
import com.outworkers.phantom.dsl._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.{Failure, Success}
import routes.Routes

object Main extends App with Routes {
  val config = ConfigFactory.load()
  implicit val system = ActorSystem(config.getString("app.name"), config)
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher
  val settings = Settings(system)

  val log: LoggingAdapter = system.log
  val appDb: AppDatabase = AppDatabase(settings)
  appDb.create(30.seconds)

  override val postGISService: PostGISService = PostGISService(appDb.postGISItems)

  val bindingFuture = Http().bindAndHandle(routes, settings.server.host, settings.server.port).onComplete {
    case Success(binding) =>
      log.info(s"Ready at ${binding.localAddress}")
    case Failure(ex) =>
      log.error(s"Failed on startup: ${ex.getMessage}")
      system.terminate()
  }
}