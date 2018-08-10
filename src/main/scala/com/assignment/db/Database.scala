package com.assignment.db

import com.outworkers.phantom.connectors.{ CassandraConnection, ContactPoint }
import com.outworkers.phantom.database.Database
import com.assignment.Settings
class AppDatabase(override val connector: CassandraConnection) extends Database[AppDatabase](connector) {
  object postGISItems extends PostGISImpl with Connector
}
object AppDatabase {
  def apply(settings: Settings): AppDatabase = {
    val cassandraConfig = settings.database
    lazy val connector: CassandraConnection =
      ContactPoint(cassandraConfig.host, cassandraConfig.port)
        .keySpace(cassandraConfig.keyspace, cassandraConfig.autoInit)
    new AppDatabase(connector)
  }
}
