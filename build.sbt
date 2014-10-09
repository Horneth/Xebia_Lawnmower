name := """mower"""

version := "1.0"

scalaVersion := "2.11.1"

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")

// Change this to another test framework if you prefer
libraryDependencies += "org.specs2" %% "specs2" % "2.4.6" % "test"
