package com.assignment

import akka.actor.ActorSystem
import com.typesafe.config.Config

class Settings(config: Config) {
  def this(system: ActorSystem) = this(system.settings.config)
  object server {
    val host: String = config.getString("app.http.host")
    val port: Int = config.getString("app.http.port").toInt
    val context: String = config.getString("app.http.context")
  }
}
object Settings {
  def apply(config: Config): Settings = new Settings(config)
  def apply(system: ActorSystem): Settings = new Settings(system)
}