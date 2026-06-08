ThisBuild / name := "sbt-openapi-generator"
ThisBuild / description :=
  """
This plugin supports common functionality found in Open API Generator CLI as a sbt plugin.

This gives you the ability to generate client SDKs, documentation, new generators, and to validate Open API 2.0 and 3.x
specifications as part of your build. Other tasks are available as command line tasks.
"""

lazy val scala21220 = "2.12.20"
lazy val scala384 = "3.8.4"

onLoadMessage := s"Welcome to sbt-openapi-generator ${version.value}"
//crossScalaVersions := Nil
//publish / skip := true // don't publish the root project

lazy val `sbt-openapi-generator` = (project in file("."))
  .settings(
    inThisBuild(
      List(
        homepage := Some(url("https://openapi-generator.tech")),

        organization := "org.openapitools",
        organizationName := "OpenAPI-Generator Contributors",
        organizationHomepage := Some(url("https://github.com/OpenAPITools")),

        licenses += (
          "The Apache Software License, Version 2.0",
          url("https://www.apache.org/licenses/LICENSE-2.0.txt")
        ),

        developers += Developer(
          id = "openapitools",
          name = "OpenAPI-Generator Contributors",
          email = "team@openapitools.org",
          url = url("https://github.com/OpenAPITools")
        )
      )
    ),
    moduleName := "sbt-openapi-generator",
    // crossScalaVersions := Seq(scala21220),
    crossScalaVersions := Seq(scala21220, scala384),
    sbtPlugin := true,
    scalacOptions ++= {
      scalaBinaryVersion.value match {
        case "2.12" => Seq("-Xsource:3", "-release:11")
        case _      => Seq("-release:17")
      }
    },
    (pluginCrossBuild / sbtVersion) := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.12.11"
        case _      => "2.0.0-RC15"
      }
    },
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++ Seq(
        "-Xmx1024M",
        "-server",
        "-Dplugin.version=" + version.value
      )
    },

    scriptedBufferLog := false,

    resolvers ++= Seq(
      Resolver.sbtPluginRepo("snapshots")
    ),

    libraryDependencies += "org.openapitools" % "openapi-generator" % "7.23.0"
  )
  .enablePlugins(SbtPlugin)
