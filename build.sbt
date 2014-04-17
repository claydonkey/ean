import play.Project._

name := "ean"

version := "1.0"

organization := "com.claydonkey"

libraryDependencies ++= Seq(
  "net.sf.barcode4j" % "barcode4j" % "2.1"
)     

playScalaSettings
