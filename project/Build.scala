import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "ean-scala"
  val appVersion      = "1.0"

  val appDependencies = Seq(
    "net.sf.barcode4j" % "barcode4j" % "2.1"
  )
  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += Resolver.sonatypeRepo("snapshots")
  )
}
