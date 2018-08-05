package com.assignment.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

trait Routes {
  val routes: Route = {
    path("test") {
      get {
        complete("test")
      }
    }
  }
}
