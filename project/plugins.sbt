// Automate file headers
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.6.0")

// Code formatting
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.2")

// Scala version management from travis.yml
addSbtPlugin("com.dwijnand" % "sbt-travisci" % "1.2.0")

// Get version from Git
addSbtPlugin("com.dwijnand" % "sbt-dynver" % "4.1.1")

// Publish to bintray
addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.6.1")
