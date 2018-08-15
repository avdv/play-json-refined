lazy val root = (project in file("."))
  .settings(
    // Build metadata for this project
    name := "play-json-refined",
    organization := "com.lunaryorn",
    organizationName := "Sebastian Wiesner",
    homepage := Some(url("https://github.com/lunaryorn/play-json-refined")),
    licenses += "Apache-2.0" -> url(
      "http://www.apache.org/licenses/LICENSE-2.0"),
    developers := List(
      Developer(
        id = "lunaryorn",
        name = "Sebastian Wiesner",
        email = "sebastian@swsnr.de",
        url = url("https://swsnr.de")
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/lunaryorn/play-json-refined"),
        "scm:git:https://github.com/lunaryorn/play-json-refined.git",
        Some(s"scm:git:git@github.com:lunaryorn/play-json-refined.git")
      )),
    description := "Play JSON Reads/Writes for refined types",
    startYear := Some(2016),
    // Dependencies
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.6.9",
      "eu.timepit" %% "refined" % "0.8.7",
      "org.scalacheck" %% "scalacheck" % "1.13.5" % "test"
    ),
    // Compiler flags.  The scala version comes from sbt-travisci
    scalacOptions ++= Seq(
      // Code encoding
      "-encoding",
      "UTF-8",
      // Deprecation warnings
      "-deprecation",
      // Warnings about features that should be imported explicitly
      "-feature",
      // Enable additional warnings about assumptions in the generated code
      "-unchecked",
      // Recommended additional warnings
      "-Xlint",
      // Warn when argument list is modified to match receiver
      "-Ywarn-adapted-args",
      // Warn about dead code
      "-Ywarn-dead-code",
      // Warn about inaccessible types in signatures
      "-Ywarn-inaccessible",
      // Warn when non-nullary overrides a nullary (def foo() over def foo)
      "-Ywarn-nullary-override",
      // Warn when numerics are unintentionally widened
      "-Ywarn-numeric-widen",
      // Fail compilation on warnings
      "-Xfatal-warnings"
    )
  )
  .enablePlugins(AutomateHeaderPlugin)
