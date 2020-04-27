package com.github.example

import java.util.Properties

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
import com.github.example.util.ElasticSearchConfig._
import org.apache.http.HttpStatus

object flinkStreamApplication {
  def main(args: Array[String]): Unit = {
    val jsonParser: ObjectMapper = new ObjectMapper()
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    searchAndCreateIndex
    val stream = env
      .addSource(new FlinkKafkaConsumer[String]("TWEET_TOPIC_STREAM", new SimpleStringSchema(), properties))
      .map(value => jsonParser.readValue(value, classOf[JsonNode]))
      .filter(value => {
      value.has("user") && value.get("lang").asText().equals("pt")
    })
      .map(value => (value.get("text").asText()))
      .map(value => insertText(value))



    env.execute("Kafka Consumer")
  }
}
