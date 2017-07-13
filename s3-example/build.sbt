import Dependencies._

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.11.158"
libraryDependencies += "com.github.pathikrit" % "better-files_2.12" % "3.0.0"

lazy val s3 = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "s3-example",
    libraryDependencies += scalaTest % Test
  )
