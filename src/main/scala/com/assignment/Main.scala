package com.assignment

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.Http
import scala.concurrent.ExecutionContext
import scala.util.{ Failure, Success }
import routes.Routes

object Main extends App with Routes {
  val config = ConfigFactory.load()
  implicit val system = ActorSystem(config.getString("app.name"), config)
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher
  val settings = Settings(system)
  val bindingFuture = Http().bindAndHandle(routes, settings.server.host, settings.server.port).onComplete {
    case Success(binding) =>
      println(s"Ready at ${binding.localAddress}")
    case Failure(ex) =>
      println(s"Failed on startup: ${ex.getMessage}")
      system.terminate()
  }
}