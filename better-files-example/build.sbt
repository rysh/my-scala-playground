import Dependencies._

libraryDependencies += "com.github.pathikrit" % "better-files_2.12" % "3.0.0"

lazy val betterFiles = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.2",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "better-files-example",
    libraryDependencies += scalaTest % Test
  )
