scalaVersion := "2.12.10"

externalResolvers += Resolver.sonatypeRepo("snapshots")

Global / onChangedBuildSource := IgnoreSourceChanges

lazy val generated = project.in(file("generated"))
  .enablePlugins(OpenApiGeneratorPlugin)
  .settings(
    openApiInputSpec := "openapi.yaml",
    openApiConfigFile := "config.yaml",
    openApiValidateSpec := SettingDisabled,
    openApiGenerateModelTests := SettingEnabled
  )

lazy val root = (project in file("."))
  .settings(
    name := "openapi-generator-example"
  )
  .dependsOn(generated)
  .aggregate(generated)
