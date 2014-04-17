import sbt._

object ApplicationBuild extends Build {

  val appName = "ean"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "net.sf.barcode4j" % "barcode4j" % "2.1"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(

  )

}
