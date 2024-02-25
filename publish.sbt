ThisBuild / organization := "org.openapitools.sbt-openapi-generator"
ThisBuild / organizationName := "OpenAPITools"
ThisBuild / organizationHomepage := Some(url("http://openapitools.org"))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/openapitools/sbt-openapi-generator"),
    "scm:git@github.com:openapitools/sbt-openapi-generator.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id = "openapitools",
    name = "OpenAPI Tools",
    email = "team@openapitools.org",
    url = url("http://openapitools.org")
  )
)

ThisBuild / description := "openapi-generator sbt plugin."
ThisBuild / licenses := List(
  "Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")
)
ThisBuild / homepage := Some(url("https://github.com/openapitools/sbt-openapi-generator"))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  // For accounts created after Feb 2021:
  // val nexus = "https://s01.oss.sonatype.org/"
  val nexus = "https://oss.sonatype.org/"
  //if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  //else Some("releases" at nexus + "service/local/staging/deploy/maven2")
  Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true
