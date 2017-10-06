import scala.sys.process._
import ReleaseTransformations._

// Do-it-all command for Travis CI
val validateCommands = List(
  "clean",
  "headerCheck",
  "test:headerCheck",
  "compile",
  "test:compile",
  "scalafmt::test",
  "test:scalafmt::test",
  "sbt:scalafmt::test",
  "test"
)
addCommandAlias("validate", validateCommands.mkString(";", ";", ""))

// Additional release step to update our README
lazy val updateVersionInReadme: ReleaseStep = { st: State =>
  val newVersion = Project.extract(st).get(version)

  val pattern = "\"com.lunaryorn\" %% \"play-json-refined\" % \"([^\"]+)\"".r
  val readme = file("README.md")
  val content = IO.read(readme)
  pattern.findFirstMatchIn(content).foreach { m =>
    IO.write(readme, m.before(1) + newVersion + m.after(1))
  }

  Seq("git", "add", readme.getAbsolutePath) !! st.log

  st
}

lazy val root = (project in file("."))
  .settings(
    // Build metadata for this project
    name := "play-json-refined",
    description := "Play JSON Reads/Writes for refined types",
    startYear := Some(2016),
    // Release configuration: Publish signed artifacts to Maven Central
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    publishMavenStyle := true,
    publishTo := Some(sonatypeDefaultResolver.value),
    // Dependencies
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.5.0",
      "eu.timepit" %% "refined" % "0.8.4",
      "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
    ),
    // Settings for the entire build.  We use sbt-travisci to get supported
    // Scala versions from .travis.yml
    inThisBuild(
      List(
        // General metadata
        organization := "com.lunaryorn",
        organizationName := "Sebastian Wiesner",
        homepage := Some(url("https://github.com/lunaryorn/play-json-refined")),
        licenses += "Apache-2.0" -> url(
          "http://www.apache.org/licenses/LICENSE-2.0"),
        developers := List(
          Developer(
            id = "lunaryorn",
            name = "Sebastian Wiesner",
            email = "swiesner@lunaryorn.com",
            url = url("http://www.lunaryorn.com")
          )
        ),
        scmInfo := Some(ScmInfo(
          url("https://github.com/lunaryorn/play-json-refined"),
          "scm:git:https://github.com/lunaryorn/play-json-refined.git",
          Some(s"scm:git:git@github.com:lunaryorn/play-json-refined.git")
        )),
        scalafmtVersion := "1.3.0",
        // General release settings
        releaseTagComment := s"play-json-refined ${version.value}",
        releaseCommitMessage := s"Bump version to ${version.value}",
        releaseProcess := Seq[ReleaseStep](
          checkSnapshotDependencies,
          inquireVersions,
          runTest,
          setReleaseVersion,
          updateVersionInReadme,
          commitReleaseVersion,
          tagRelease,
          publishArtifacts,
          setNextVersion,
          commitNextVersion,
          pushChanges,
          releaseStepCommand("sonatypeRelease")
        ),
        // Credentials for Travis CI, see
        // http://www.cakesolutions.net/teamblogs/publishing-artefacts-to-oss-sonatype-nexus-using-sbt-and-travis-ci
        credentials ++= (for {
          username <- Option(System.getenv().get("SONATYPE_USERNAME"))
          password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
        } yield
          Credentials("Sonatype Nexus Repository Manager",
                      "oss.sonatype.org",
                      username,
                      password)).toSeq,
        // Build settings
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
    )
  )
  .enablePlugins(AutomateHeaderPlugin)
