import play.Project._

name := "ean"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "net.sf.barcode4j" % "barcode4j" % "2.1"
)     

playScalaSettings
