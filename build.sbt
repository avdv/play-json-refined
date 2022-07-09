import java.time.LocalDate

import complete.DefaultParsers._

import scala.sys.process._

val release = inputKey[Unit]("Commit and tag a release")

lazy val root = (project in file("."))
  .settings(
    // Build metadata for this project
    name := "play-json-refined",
    organization := "de.cbley",
    organizationName := "Claudio Bley",
    crossScalaVersions := List("2.13.8", "2.12.15", "3.1.3"),
    homepage := Some(url("https://github.com/avdv/play-json-refined")),
    licenses += "Apache-2.0" -> url(
      "http://www.apache.org/licenses/LICENSE-2.0"
    ),
    developers := List(
      Developer(
        id = "lunaryorn",
        name = "Sebastian Wiesner",
        email = "sebastian@swsnr.de",
        url = url("https://swsnr.de")
      ),
      Developer(
        id = "avdv",
        name = "Claudio Bley",
        email = "claudio.bley@gmail.com",
        url = url("https://github.com/avdv")
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/avdv/play-json-refined"),
        "scm:git:https://github.com/avdv/play-json-refined.git",
        Some(s"scm:git:git@github.com:avdv/play-json-refined.git")
      )
    ),
    description := "Play JSON Reads/Writes for refined types",
    startYear := Some(2019),
    // Dependencies
    libraryDependencies ++= {
      val isScala3 = scalaVersion.value.startsWith("3.")

      Seq(
        "com.typesafe.play" %% "play-json" % (if (isScala3) "2.10.0-RC6"
                                              else "2.9.2"),
        "eu.timepit" %% "refined" % "0.9.29",
        "org.scalacheck" %% "scalacheck" % "1.16.0" % Test,
        "eu.timepit" %% "refined-scalacheck" % "0.9.29" % Test
      )
    },
    // Compiler flags.
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
      // Fail compilation on warnings
      "-Xfatal-warnings"
    ) ++ (scalaBinaryVersion.value match {
      case "2.12" =>
        Seq(
          // enable higher-kinded types, (no longer needed in Scala 2.13.1)
          "-language:higherKinds",
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
          // Recommended additional warnings
          "-Xlint",
          // Warn about unused things
          "-Ywarn-unused"
        )

      case "2.13" => Seq("-Xlint")

      case _ => Seq.empty[String]
    }),
    // Make a release
    release := {
      val log = streams.value.log

      // Only allow releases from clean repos.
      val status = Seq("git", "status", "--porcelain") !! log
      if (status.trim.nonEmpty) {
        log.error("Repository dirty!  Please commit all changes first!")
        throw new IllegalStateException()
      }

      val newVersion = (Space ~ NotSpace).parsed._2

      log.info(s"Updating changelog for version $newVersion")

      val pattern = "## \\[?Unreleased\\]?".r
      val changelog = file("CHANGELOG.md")
      val content = IO.read(changelog)

      pattern.findFirstMatchIn(content) match {
        case Some(m) =>
          IO.write(
            changelog,
            m.before(0) + m.matched + s"\n\n## $newVersion – ${LocalDate.now().toString}" + m
              .after(0)
          )
        case None =>
          throw new IllegalStateException(
            "Failed to find Unreleased section in CHANGELOG"
          )
      }

      def check(code: Int): Unit =
        if (code != 0) {
          log.error(s"Exited $code")
          throw new Exception()
        }

      log.info(s"Commit $changelog")
      check(Seq("git", "add", changelog.getAbsolutePath) ! log)
      check(Seq("git", "commit", "-m", s"Release $newVersion") ! log)
      val tagName = s"v$newVersion"
      log.info(s"Make tag $tagName")
      check(
        Seq("git", "tag", "-sm", s"${name.value} $newVersion", tagName) ! log
      )
      log.info(s"New version $newVersion ready.")
      log.info(
        "Check everything, and then run 'git push --follow-tags' to publish"
      )
    }
  )
  .enablePlugins(AutomateHeaderPlugin)
