name := "play-json-refined"
organization := "com.lunaryorn"
description := "Play JSON Reads/Writes for refined types"
homepage := Some(url(s"https://github.com/lunaryorn/play-json-refined"))
startYear := Some(2016)
licenses += "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
scmInfo := Some(ScmInfo(homepage.value.get,
  s"scm:git:https://github.com/lunaryorn/play-json-refined.git",
  Some(s"scm:git:git@github.com:lunaryorn/play-json-refined.git")))
pomExtra :=
    <developers>
      <developer>
        <id>lunaryorn</id>
        <name>Sebastian Wiesner</name>
        <url>http://www.lunaryorn.com</url>
      </developer>
    </developers>

// Build settings
scalaVersion := "2.11.8"
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.5.0",
  "eu.timepit" %% "refined" % "0.4.0",
  "org.scalacheck" %% "scalacheck" % "1.13.0" % "test"
)

// Automatically update file headers
enablePlugins(AutomateHeaderPlugin)
import de.heikoseeberger.sbtheader.license.Apache2_0
headers := Map(
  "scala" -> Apache2_0("2016", "Sebastian Wiesner")
)
// TODO: Use with next sbt-headers version
// import de.heikoseeberger.sbtheader.CommentStyleMapping._
//headers := createFrom(Apache2_0, "2015", "Heiko Seeberger")

initialCommands := s"""
import eu.timepit.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.api._
import play.api.libs.json._
import com.lunaryorn.refined.play.json._
"""

val validateCommands = List(
  "clean",
  "scalafmtTest", "test:scalafmtTest",
  "compile", "test:compile",
  "test"
)
addCommandAlias("validate", validateCommands.mkString(";", ";", ""))
