ThisBuild / name := "sbt-openapi-generator"
ThisBuild / description :=
  """
This plugin supports common functionality found in Open API Generator CLI as a sbt plugin.

This gives you the ability to generate client SDKs, documentation, new generators, and to validate Open API 2.0 and 3.x
specifications as part of your build. Other tasks are available as command line tasks.
"""

lazy val `sbt-openapi-generator` = (project in file("."))
  .settings(
    scalaVersion := "2.12.10",
    crossScalaVersions := Seq(scalaVersion.value, "2.11.12"),
    crossSbtVersions := List("0.13.17", "1.3.10"),
    sbtPlugin := true,

    publishMavenStyle := false,

    bintrayRepository := "sbt-plugins",
    bintrayOrganization := Option("openapitools"),
    bintrayPackageLabels := Seq("sbt", "plugin", "oas", "openapi", "openapi-generator"),
    bintrayVcsUrl := Some("git@github.com:OpenAPITools/sbt-openapi-generator.git"),

    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-server", "-Dplugin.version=" + version.value)
    },

    scriptedBufferLog := false,

    resolvers ++= Seq(
      Resolver.sbtPluginRepo("snapshots"),
      Resolver.sonatypeRepo("snapshots")
    ),

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
    ),

    scmInfo := Some(
      ScmInfo(
        browseUrl = url("https://github.com/OpenAPITools/openapi-generator"),
        connection = "scm:git:git://github.com/OpenAPITools/openapi-generator.git",
        devConnection = "scm:git:ssh://git@github.com:OpenAPITools/openapi-generator.git")
    ),

    libraryDependencies += "org.openapitools" % "openapi-generator" % "5.0.0-beta2"
  ).enablePlugins(SbtPlugin)
