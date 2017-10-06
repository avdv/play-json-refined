// Automate file headers
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "3.0.2")

// Code formatting
addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.12")

// Scala version management from travis.yml
addSbtPlugin("com.dwijnand" % "sbt-travisci" % "1.1.1")

// Plugins for releases
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.6")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.0")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.0")
