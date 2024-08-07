import sbt.Keys.libraryDependencies

ThisBuild / organization := "mikey corp"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.8"

autoCompilerPlugins := true

val kindProjector = addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full) // doesnt work wip maybe
addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full) // doesnt work wip maybe


lazy val root = (project in file("."))
  .settings(
    name := "scala-notes-and-examples",
    libraryDependencies ++= Seq(
      "dev.doamaral" %% "zio-selenium" % "1.0.0",
      "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1",
      "org.scalactic" %% "scalactic" % "3.2.14",
      "org.typelevel" %% "cats-core" % "2.9.0", // cats for functional programming
      "org.typelevel" %% "cats-effect" % "3.2.0", // effect-system
      "com.disneystreaming" %% "weaver-cats" % "0.8.1" % Test, //disney streaming
      "com.disneystreaming" %% "weaver-scalacheck" % "0.8.1" % Test,
      "org.scalamock" %% "scalamock" % "5.2.0" % Test,
      "org.scalatest" %% "scalatest" % "3.2.14" % Test,
      "org.scalacheck" %% "scalacheck" % "1.17.0" % Test, //property testing,
      "joda-time" % "joda-time" % "2.12.7",

      "org.seleniumhq.selenium" % "selenium-java" % "3.141.59",
      "org.seleniumhq.selenium" % "selenium-chrome-driver" % "3.141.59",
      "org.typelevel" %% "cats-effect" % "3.2.9",
      "co.fs2" %% "fs2-core" % "3.1.3"
    ),
    testFrameworks += new TestFramework("weaver.framework.CatsEffect"),
    scalacOptions ++=
      Seq("-language:higherKinds"),
    kindProjector
  )

lazy val testSettings: Seq[Def.Setting[_]] = {
  Seq(
    fork := true,
    javaOptions ++= Seq(
      "-Dconfig.resource=test.application.conf",
      "-Dlogger.resource=logback-test.xml"
    )
  )
}





