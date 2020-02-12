// Automate file headers
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.3.1")

// Code formatting
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.0.0")

// Scala version management from travis.yml
addSbtPlugin("com.dwijnand" % "sbt-travisci" % "1.2.0")

// Get version from Git
addSbtPlugin("com.dwijnand" % "sbt-dynver" % "4.0.0")

// Publish to bintray
addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.5")
