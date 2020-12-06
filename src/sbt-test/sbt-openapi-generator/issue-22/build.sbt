scalaVersion := "2.12.10"

externalResolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .enablePlugins(OpenApiGeneratorPlugin)
  .settings(
    openApiInputSpec := "openapi.yaml",
    openApiConfigFile := "config.json",
    openApiGeneratorName := "scala-sttp",
    openApiOutputDir := "api"
  )

TaskKey[Unit]("check") := {
  val `build.sbt` = IO.readLines(file("api/build.sbt")).mkString
  if (!`build.sbt`.contains("circe"))
    throw new Exception("generated build did not contain circe")
  if (!`build.sbt`.contains("2.2.9"))
    throw new Exception("generated build did not contain circe")
  ()
}
