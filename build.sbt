name := "Assignment"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= {
  val akkaVersion = "2.5.13"
  val akkaHttpVersion = "10.1.1"
  Seq(
    "com.typesafe.akka"   %% "akka-actor"        % akkaVersion,
    "com.typesafe.akka"   %% "akka-stream"       % akkaVersion,
    "com.typesafe.akka"   %% "akka-http"         % akkaHttpVersion,
    "org.scalatest"       %% "scalatest"         % "3.2.0-SNAP10" % "test",
    "org.slf4j"           % "jcl-over-slf4j"     % "1.7.25",
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
}