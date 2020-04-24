package com.github.example

import java.util.Properties

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.SimpleStringSchema

object flinkStreamApplication {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    val stream = env
      .addSource(new FlinkKafkaConsumer[String]("TWEET_TOPIC_STREAM", new SimpleStringSchema(), properties))
      .print()
    env.execute("Kafka Consumer")
  }
}
