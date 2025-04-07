ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "smart-budget-guardian",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.5.2",
      "com.typesafe.akka" %% "akka-stream" % "2.8.4",
      "com.typesafe.akka" %% "akka-actor-typed" % "2.8.4",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.2",
      "com.typesafe.slick" %% "slick" % "3.5.1",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.5.1",
      "mysql" % "mysql-connector-java" % "8.0.33",
      "org.apache.spark" % "spark-core_2.12" % "3.5.1",
      "org.apache.spark" % "spark-sql_2.12" % "3.5.1",
      "ch.qos.logback" % "logback-classic" % "1.4.7"
    )
  )
