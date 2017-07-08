// Automate file headers
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "1.8.0")

// Code formatting
addSbtPlugin("com.geirsson" %% "sbt-scalafmt" % "0.5.7")

// Scala version management from travis.yml
addSbtPlugin("com.dwijnand" % "sbt-travisci" % "1.1.0")

// Plugins for releases
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.4")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.1")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "1.1")
