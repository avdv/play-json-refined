// Automate file headers
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.0.0")

// Code formatting
addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.15")

// Scala version management from travis.yml
addSbtPlugin("com.dwijnand" % "sbt-travisci" % "1.1.1")

// Plugins for releases
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.0")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.0")
