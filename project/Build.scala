import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "ToDo"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "org.scalatest" %% "scalatest" % "1.8" % "test",
    "org.squeryl" %% "squeryl" % "0.9.5-2",
    "postgresql" % "postgresql" % "9.1-901.jdbc4")

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings( // Add your own project settings here      
    testOptions in Test := Nil)
}
