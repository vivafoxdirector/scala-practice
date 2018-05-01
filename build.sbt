name := "scala-practice"
version := "1.0"
scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
    "org.scalikejdbc" %% "scalikejdbc"       % "3.2.2",
    "org.scalikejdbc" %% "scalikejdbc-config" % "3.0.0",
    "org.scalikejdbc" %% "scalikejdbc-joda-time" % "3.2.2",
    "com.h2database"  %  "h2"                % "1.4.197",
    "ch.qos.logback"  %  "logback-classic"   % "1.2.3"
)
    