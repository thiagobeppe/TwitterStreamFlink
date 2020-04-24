package com.github.example

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object flinkStreamApplication {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
  }
}
