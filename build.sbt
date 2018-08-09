name := "Assignment"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= {
  val akkaVersion = "2.5.13"
  val akkaHttpVersion = "10.1.1"
  val circeVersion = "0.9.0"
  val phantomVersion = "2.18.0"
  Seq(
    "com.typesafe.akka"   %% "akka-actor"        % akkaVersion,
    "com.typesafe.akka"   %% "akka-stream"       % akkaVersion,
    "com.typesafe.akka"   %% "akka-http"         % akkaHttpVersion,
    "com.typesafe.akka"   %% "akka-slf4j"        % akkaVersion,
    "org.scalatest"       %% "scalatest"         % "3.2.0-SNAP10" % "test",
    "org.slf4j"           % "jcl-over-slf4j"     % "1.7.25",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "io.circe"            %% "circe-core"        % circeVersion,
    "io.circe"            %% "circe-generic"     % circeVersion,
    "io.circe"            %% "circe-parser"      % circeVersion,
    "de.heikoseeberger"   %% "akka-http-circe"   % "1.18.0",
    "com.outworkers"      %% "phantom-dsl"       % phantomVersion,
    "com.outworkers"      %% "phantom-jdk8"      % phantomVersion,
    "org.codehaus.groovy" % "groovy"             % "2.4.12"
  )
}