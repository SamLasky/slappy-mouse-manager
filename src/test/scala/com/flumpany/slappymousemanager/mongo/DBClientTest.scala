package com.flumpany.slappymousemanager.mongo

import org.mongodb.scala.bson.BsonString
import org.scalatest.{FunSpec, Matchers}
import org.mongodb.scala.{Completed, Document}
import org.mongodb.scala.model.Filters.{and, equal => mongoEqual}
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.duration.Duration
import scala.util.Random

class DBClientTest extends FunSpec with Matchers with ScalaFutures {
  describe("DBClient") {
    val host = "mongodb://localhost:27017"
    val testDatabase = Random.nextString(10)
    val testCollection = Random.nextString(10)
    describe("create"){
      it("should take a Document, write the document to a collection, and return a Completed message "){

        val client = new DBClient(host, testDatabase, testCollection)
        val testName = "test1"
        val testDocument = UserData(testName)
        val completedFuture = client.create(testDocument)

        whenReady(completedFuture){ completed: Completed =>
          completed.toString() shouldBe "The operation completed successfully"

          val condition = mongoEqual("name", testName)

          val documentFuture = client.read(condition)
          whenReady(documentFuture){ userDatas =>

            val testUserData = UserData(testName)
            val actualUserData = userDatas.head
            actualUserData shouldBe testUserData
            actualUserData.name shouldBe testUserData.name
          }
        }
      }
    }

  }
}