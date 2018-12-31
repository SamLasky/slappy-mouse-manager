package com.flumpany.slappymousemanager.mongo

import org.mongodb.scala._

object DBClient {

  val mongoClient: MongoClient = MongoClient("mongodb://localhost")

  val database: MongoDatabase = mongoClient.getDatabase("mydb")

  val collection: MongoCollection[Document] = database.getCollection("test")

  def createInTestCollection(newDoc:Document) = {
    collection.insertOne(newDoc)
  }

  def read(name:Document) = {
    collection.find(name)
  }
}




