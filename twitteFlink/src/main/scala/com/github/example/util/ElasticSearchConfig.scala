package com.github.example.util

import com.sksamuel.elastic4s.http.JavaClient
import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties, RequestFailure, RequestSuccess}
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.requests.common.RefreshPolicy
import com.sksamuel.elastic4s.requests.mappings.{FieldDefinition, MappingDefinition}
import com.sksamuel.elastic4s.requests.searches.SearchResponse
import org.apache.http.HttpStatus

object ElasticSearchConfig {
  private val port = 9200
  private val host = "localhost"

  val client = ElasticClient(JavaClient(ElasticProperties(s"http://${host}:${port}")))

  def createTweetIndex(): Unit ={
    val resp = client.execute {
      createIndex("Tweet").mapping(new MappingDefinition(fields = Seq[FieldDefinition] =()))
    }.await

    println(resp)
  }

  def insertText(str : String): Unit = {
    val resp = client.execute {
      indexInto("tweet").fields("tweetText" -> str).refresh(RefreshPolicy.Immediate)
    }.await
    println(resp)
  }

  def searchIndex(): Int = {
    val resp = client.execute( search("tweet")).await

    println("---- Search Results ----")
    resp match {
      case failure: RequestFailure => {
        println("We failed " + failure.error)
        HttpStatus.SC_BAD_REQUEST
      }
      case results: RequestSuccess[SearchResponse] => HttpStatus.SC_ACCEPTED
      case results: RequestSuccess[_] => HttpStatus.SC_ACCEPTED
    }
  }

}
