// Automate file headers
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "1.6.0")

// Formatting
addSbtPlugin("com.geirsson" %% "sbt-scalafmt" % "0.5.1")

// Bintray publishing
addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")

// Releasing
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.3")

// GPG signing
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")
