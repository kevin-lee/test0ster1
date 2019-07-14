import kevinlee.sbt.SbtCommon._
import kevinlee.sbt.devoops.data.SbtTaskError
import kevinlee.sbt.io.CaseSensitivity
import org.scoverage.coveralls.Imports.CoverallsKeys._

ThisBuild / organization := "kevinlee"
ThisBuild / version := "0.0.7"
ThisBuild / scalaVersion := "2.12.8"

val projectBuildSourceEncoding = "UTF-8"
val javaVersion = "1.8"
val junitJupiterVersion = "5.5.0"

lazy val test0ster1 = (project in file("."))
    .settings(
      name := "test0ster1"
    )
    .enablePlugins(DevOopsJavaPlugin)
    .enablePlugins(DevOopsGitReleasePlugin)
    .settings(
      libraryDependencies ++= Seq(
        "org.junit.jupiter" % "junit-jupiter" % junitJupiterVersion % Test,
        "net.aichler" % "jupiter-interface" % "0.8.2" % Test,
        "org.assertj" % "assertj-core" % "3.12.2" % Test,
        "org.mockito" % "mockito-core" % "3.0.0" % Test
      )
    , testOptions += Tests.Argument(TestFrameworks.JUnit, "-a")

    , bintrayPackageLabels := Seq("maven", "java", "test", "java8")
    , bintrayVcsUrl := Some("git@github.com:Kevin-Lee/test0ster1.git")
    , bintrayRepository := "maven"

    , publishMavenStyle := true
    , publishArtifact in Test := false
    , pomIncludeRepository := { _ => false }
    , pomExtra := (
      <url>https://github.com/Kevin-Lee/test0ster1</url>
        <licenses>
          <license>
            <name>The Apache License</name>
            <url>https://github.com/Kevin-Lee/test0ster1/blob/master/LICENSE</url>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:Kevin-Lee/test0ster1.git</url>
          <connection>scm:git:git@github.com:Kevin-Lee/test0ster1.git</connection>
        </scm>)
    , licenses += ("Apache-2.0", url("http://opensource.org/licenses/apache2.0"))
    , coverallsTokenFile := Option(s"""${sys.props("user.home")}/.coveralls-credentials""")
    /* GitHub Release { */
    , gitTagFrom := "master"
    // FIXME: remove it once sbt-devoops has the jar file path configurable.
    , devOopsCopyReleasePackages := {
        val result: Vector[File] = copyFiles(
          CaseSensitivity.caseSensitive
          , baseDirectory.value
          , List(s"target/${name.value}*.jar")
          , new File(new File(devOopsCiDir.value), "dist")
        ) match {
          case Left(error) =>
            messageOnlyException(SbtTaskError.render(error))
          case Right(files) =>
            files
        }
        result
      }
    /* } GitHub Release */
    )



