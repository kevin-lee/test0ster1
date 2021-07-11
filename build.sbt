import ProjectInfo._

ThisBuild / organization := "io.kevinlee"
ThisBuild / version := ProjectVersion
ThisBuild / scalaVersion := "2.13.3"

ThisBuild / developers := projectDevelopers
ThisBuild / homepage := projectHomePage
ThisBuild / scmInfo := projectScmInfo

val projectBuildSourceEncoding = "UTF-8"
val junitJupiterVersion        = "5.6.2"

lazy val test0ster1 = (project in file("."))
  .enablePlugins(DevOopsJavaPlugin)
  .enablePlugins(DevOopsGitHubReleasePlugin)
  .enablePlugins(JacocoCoverallsPlugin)
  .settings(
    name := "test0ster1",
    javacOptions := List(
      "-source",
      javaVersion.value,
      "-encoding",
      "UTF-8"
    ),
    Compile / compile / javacOptions ++= List(
      "-target",
      javaVersion.value,
      "-Xlint:unchecked",
      "-g",
      "-deprecation"
    ),
    Compile / test / javacOptions := (Compile / compile / javacOptions).value,
    libraryDependencies ++= List(
      "org.junit.jupiter" % "junit-jupiter"     % junitJupiterVersion              % Test,
      "net.aichler"       % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test,
      "org.assertj"       % "assertj-core"      % "3.17.2"                         % Test,
      "org.mockito"       % "mockito-core"      % "3.5.10"                         % Test
    ),
    testOptions += Tests.Argument(TestFrameworks.JUnit, "-a")

    /* Jacoco { */,
    jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report",
      None,
      JacocoThresholds(),
      List(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML),
      "utf-8"
    ),
    jacocoCoverallsServiceName := "github-actions",
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")
    /* } Jacoco */,
    publishMavenStyle := true,
    Test / publishArtifact := false,
    licenses := List("Apache-2.0" -> url("http://opensource.org/licenses/apache2.0"))
    /* GitHub Release { */,
    devOopsPackagedArtifacts := List(s"target/${name.value}*.jar")
    /* } GitHub Release */
  )
