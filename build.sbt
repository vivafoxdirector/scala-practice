name := "scala-practice"
version := "1.0"
scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
    "org.scalikejdbc" %% "scalikejdbc"       % "3.2.2",
    "org.scalikejdbc" %% "scalikejdbc-config" % "3.0.0",
    "com.h2database"  %  "h2"                % "1.4.197",
    "ch.qos.logback"  %  "logback-classic"   % "1.2.3",

    // Akka
    //"com.typesafe.akka" %% "akka-actor" % "2.4.10"//,
    "com.typesafe.akka" %% "akka-actor" % "2.5.21",
    "com.typesafe.akka" %% "akka-stream" % "2.5.21",

    // scalax
    //"com.github.scala-incubator.io" %% "scala-io-file" % "0.4.3"
    // scalaj
    "org.scalaj" %% "scalaj-http" % "2.4.1"
// Akka-Http
    //"com.typesafe.akka" %% "akka-http-experimental" % "2.4.11"
)
