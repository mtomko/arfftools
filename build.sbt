name := "arfftools"

version := "1.0.0"

scalaVersion := "2.9.1"

//resolvers += "Oracle Repository" at "http://download.oracle.com/maven/"

libraryDependencies ++= Seq(
    "org.scalaz" %% "scalaz-core" % "6.0.4",
    "junit" % "junit" % "4.8" % "test",
    "com.novocode" % "junit-interface" % "0.8" % "test",
    "org.scalatest" %% "scalatest" % "1.6.1" % "test"

    //"org.codehaus.woodstox" % "woodstox-core-asl" % "4.1.1"
    //"org.codehaus.staxmate" % "staxmate" % "2.0.0"
    //"com.sleepycat" % "je" % "5.0.34"
)