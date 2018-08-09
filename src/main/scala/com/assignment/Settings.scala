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

  object database {
    val host: String = config.getString("database.host")
    val port: Int = config.getString("database.port").toInt
    val keyspace: String = config.getString("database.keyspace")
    val autoInit: Boolean = config.getBoolean("database.initialize-keyspace")
  }
}
object Settings {
  def apply(config: Config): Settings = new Settings(config)
  def apply(system: ActorSystem): Settings = new Settings(system)
}