import com.typesafe.config.ConfigFactory
import com.typesafe.sbt.SbtNativePackager.autoImport.NativePackagerHelper._
import com.typesafe.sbt.packager.Keys.packageName


name := "scala-sbt-demo"
organization := "cc.xstack"
version := "1.0"
maintainer := "xstack@163.com"

// 编译目标
scalaVersion := "2.12.15"
javacOptions ++= Seq("-source", "11", "-target", "11", "-Xlint")

// 启用 sbt-native-packager 插件
enablePlugins(JavaAppPackaging)

Universal / name := name.value
Universal / packageName := name.value

// 资源文件、配置文件打包
Universal / mappings ++= directory("src/main/resources")
// 将配置文件路径加入自动生成的启动脚本中classpath的最前端, 如declare -r app_classpath="$lib_dir/../resources:$lib_dir/......“
bashScriptDefines / scriptClasspath ~= (cp => "../resources" +: cp)
lazy val baseConfig = ConfigFactory.load //defaults from src/resources //For use in Debain packaging script. (see etc-default)
val systemConfig = Option(System.getProperty("myapplication.config")) match {
  case Some(cfile) => ConfigFactory.parseFile(new File(cfile)).withFallback(baseConfig)
  case None => baseConfig
}
// TODO exclude file from jar

//发布到本地maven仓库时允许覆盖jar
publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)
publishM2Configuration := publishM2Configuration.value.withOverwrite(true)

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.36"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.11"
// libraryDependencies += "commons-io" % "commons-io" % "2.5"
// libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.3"
// libraryDependencies += "org.apache.zookeeper" % "zookeeper" % "3.4.13" excludeAll ExclusionRule(organization = "*")
