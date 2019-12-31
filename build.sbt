name := "scala-sbt-demo"

version := "1.0"

scalaVersion := "2.12.10"
javacOptions ++= Seq("-source", "11", "-target", "11", "-Xlint")

//libraryDependencies += "org.apache.felix" % "org.apache.felix.framework" % "1.8.0" withSources()
libraryDependencies += "org.slf4s" %% "slf4s-api" % "1.7.25"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
// libraryDependencies += "commons-io" % "commons-io" % "2.5"
//libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.3.0"
// libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0"
// libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.3"
// libraryDependencies += "org.apache.zookeeper" % "zookeeper" % "3.4.13" excludeAll ExclusionRule(organization = "*")
enablePlugins(PackPlugin)
// enablePlugins(JavaAppPackaging)
