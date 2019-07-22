import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "zio-example",
    libraryDependencies += scalaTest % Test
  )

libraryDependencies += "dev.zio" %% "zio" % "1.0.0-RC10-1"
libraryDependencies += "dev.zio"             %% "zio-interop-cats"            % "2.0.0.0-RC1"

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
