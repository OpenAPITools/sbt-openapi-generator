ThisBuild / name := "sbt-openapi-generator"
ThisBuild / description :=
  """
This plugin supports common functionality found in Open API Generator CLI as a sbt plugin.

This gives you the ability to generate client SDKs, documentation, new generators, and to validate Open API 2.0 and 3.x
specifications as part of your build. Other tasks are available as command line tasks.
"""

lazy val scala212 = "2.12.20"
lazy val scala211 = "2.11.12"

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

        licenses += ("The Apache Software License, Version 2.0", url("https://www.apache.org/licenses/LICENSE-2.0.txt")),

        developers += Developer(
          id = "openapitools",
          name = "OpenAPI-Generator Contributors",
          email = "team@openapitools.org",
          url = url("https://github.com/OpenAPITools")
        )
      )
    ),
    moduleName := "sbt-openapi-generator",
    crossScalaVersions := Seq(scala212, scala211),
    crossSbtVersions := List("1.11.4"),
    sbtPlugin := true,
    scalacOptions ++= {
      scalaBinaryVersion.value match {
        case "2.12" => "-Xsource:3" :: Nil
        case _      => Nil
      }
    },
    (pluginCrossBuild / sbtVersion) := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.5.8"
        case _      => "1.0.0-M4"
      }
    },
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-server", "-Dplugin.version=" + version.value)
    },

    scriptedBufferLog := false,

    resolvers ++= Seq(
      Resolver.sbtPluginRepo("snapshots"),
    ),


    libraryDependencies += "org.openapitools" % "openapi-generator" % "7.14.0",
  ).enablePlugins(SbtPlugin)