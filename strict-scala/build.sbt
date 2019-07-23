import Dependencies._

ThisBuild / scalaVersion := "2.12.8" // scalafixが2.13に未対応(2019/07/22)
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "strict-scala",
    addCompilerPlugin(scalafixSemanticdb),
    scalacOptions ++= List(
      "-Yrangepos",
      "-Ywarn-unused-import"
    ),
    libraryDependencies ++= Seq(
      scalaTest % Test
    )
  )

wartremoverErrors in (Compile, compile) ++= Warts.all

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
