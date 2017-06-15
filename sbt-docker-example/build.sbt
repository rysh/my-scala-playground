import Dependencies._

lazy val root = (project in file("."))
	.enablePlugins(JavaAppPackaging, DockerPlugin)
	.settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.1",
      mainClass in (Compile, run) := Some("Hello"),
      dockerBaseImage := "java:8-jdk-alpine",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test
  )

    