import play.Project._

name := "ean"

version := "1.0.1"

organization := "com.claydonkey"

resolvers += Resolver.url("Claydonkey Play Repository", url("http://claydonkey.github.io/releases/"))(Resolver.ivyStylePatterns)

resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL + "/.m2/repository"

libraryDependencies ++= Seq("org.krysalis" % "barcode4j" % "2.1-SNAPSHOT")  

//libraryDependencies ++= Seq("net.sf.barcode4j" % "barcode4j" % "2.1")

playScalaSettings
