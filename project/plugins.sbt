// Automate file headers
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.0.0")

// Code formatting
addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.15")

// Scala version management from travis.yml
addSbtPlugin("com.dwijnand" % "sbt-travisci" % "1.1.1")

// Get version from Git
addSbtPlugin("com.dwijnand" % "sbt-dynver" % "3.0.0")
