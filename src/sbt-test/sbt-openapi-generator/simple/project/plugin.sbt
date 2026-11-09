resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)
sys.props.get("plugin.version") match {
  case Some(x) => addSbtPlugin("org.openapitools" % "sbt-openapi-generator" % x)
  case _       =>
    // Fallback to the currently released version of the plugin, so the
    // bundled example project also loads on plain `sbt`. Under
    // `sbt scripted` the `plugin.version` system property is always set
    // via scriptedLaunchOpts in build.sbt, and that locally built version
    // is used instead.
    addSbtPlugin("org.openapitools" % "sbt-openapi-generator" % "7.27.0")
}
