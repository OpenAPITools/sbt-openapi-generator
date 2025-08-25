ThisBuild / name := "sbt-openapi-generator"
ThisBuild / description :=
  """
This plugin supports common functionality found in Open API Generator CLI as a sbt plugin.

This gives you the ability to generate client SDKs, documentation, new generators, and to validate Open API 2.0 and 3.x
specifications as part of your build. Other tasks are available as command line tasks.
"""

lazy val scala212 = "2.12.20"
lazy val scala3 = "3.7.2"

inThisBuild(
  List(
    homepage := Some(url("https://openapi-generator.tech")),

    organization := "org.openapitools",
    organizationName := "OpenAPI-Generator Contributors",
    organizationHomepage := Some(url("https://github.com/OpenAPITools")),

    licenses += ("The Apache Software License, Version 2.0", url("https://www.apache.org/licenses/LICENSE-2.0.txt")),

    developers += Developer(
      id = "openapitools",
      name = "OpenAPI-Generator Contributors",
      email = "team@openapitools.org",
      url = url("https://github.com/OpenAPITools")
    )
  )
)

onLoadMessage := s"Welcome to sbt-openapi-generator ${version.value}"
crossScalaVersions := Nil
publish / skip := true // don't publish the root project

lazy val plugin = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    moduleName := "sbt-openapi-generator",
    crossScalaVersions := Seq(scala212, scala3),
    scalacOptions ++= {
      scalaBinaryVersion.value match {
        case "2.12" => "-Xsource:3" :: Nil
        case _      => Nil
      }
    },
    (pluginCrossBuild / sbtVersion) := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.5.8"
        case _      => "2.0.0-RC3"
      }
    },
    libraryDependencies += "org.openapitools" % "openapi-generator" % "7.14.0",
    addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.11.2")
  )