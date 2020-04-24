name := "twitteFlink"

version := "0.1"

scalaVersion := "2.12.11"


resolvers += Resolver.sonatypeRepo("releases")


libraryDependencies += "org.apache.flink" %% "flink-clients" % "1.10.0"
libraryDependencies += "org.apache.flink" %% "flink-scala" % "1.10.0"
libraryDependencies += "org.apache.flink" %% "flink-streaming-scala" % "1.10.0"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.1"
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "2.5.0"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.30"
libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "3.0.3"
libraryDependencies += "org.apache.flink" %% "flink-connector-kafka" % "1.10.0"