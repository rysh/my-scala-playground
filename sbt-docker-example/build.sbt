import Dependencies._

lazy val root = (project in file("."))
	.enablePlugins(JavaAppPackaging, AshScriptPlugin, sbtdocker.DockerPlugin)
	.settings(
    organization := "rysh",
    scalaVersion := "2.12.1",
    name := "hello",
    mainClass in (Compile, run) := Some("Hello"),
    version      := "0.1.1-SNAPSHOT",
    dockerfile in docker := {
      // sbt-native-packager の stage タスクによってアプリがステージングされたディレクトリ
      val stageDir: File = stage.value
      val targetDir = "/opt/docker"

      new Dockerfile {
        from("java:8-jdk-alpine")
        copy(stageDir, targetDir)
        entryPoint(s"$targetDir/bin/${executableScriptName.value}")
      }
    },
    imageNames in docker := Seq(
      // Sets the latest tag
      ImageName(s"${organization.value}/${name.value}:latest"),

      // Sets a name with a tag that contains the project version
      ImageName(
        namespace = Some(organization.value),
        repository = name.value,
        tag = Some("v" + version.value)
      )
    ),
    libraryDependencies += scalaTest % Test
  )

    