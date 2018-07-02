name := "scala-practice"
version := "1.0"
scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
    "org.scalikejdbc" %% "scalikejdbc"       % "3.2.2",
    "org.scalikejdbc" %% "scalikejdbc-config" % "3.0.0",
    "com.h2database"  %  "h2"                % "1.4.197",
    "ch.qos.logback"  %  "logback-classic"   % "1.2.3",

	// Akka
	"com.typesafe.akka" %% "akka-actor" % "2.4.10"//,

	// Akka-Http
	//"com.typesafe.akka" %% "akka-http-experimental" % "2.4.11"
)
