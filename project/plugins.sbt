// Automate file headers
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "1.6.0")

// Code formatting
addSbtPlugin("com.geirsson" %% "sbt-scalafmt" % "0.5.1")

// Plugins for releases
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.3")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.1")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "1.1")
addSbtPlugin("ch.epfl.scala.index" % "sbt-scaladex" % "0.1.3")
