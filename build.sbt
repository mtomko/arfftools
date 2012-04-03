import AssemblyKeys._ // for assembly plugin

name := "arfftools"

version := "1.0.0"

scalaVersion := "2.9.1"

scalacOptions ++= Seq("-unchecked", "-deprecation")

libraryDependencies ++= Seq(
    "org.scalaz" %% "scalaz-core" % "6.0.4",
    "junit" % "junit" % "4.8" % "test",
    "com.novocode" % "junit-interface" % "0.8" % "test",
    "org.scalatest" %% "scalatest" % "1.6.1" % "test",
    "com.github.scala-incubator.io" %% "scala-io-core" % "0.3.0",
    "com.github.scala-incubator.io" %% "scala-io-file" % "0.3.0"
)

//jarName in assembly := "arfftools.jar"

seq(assemblySettings: _*)