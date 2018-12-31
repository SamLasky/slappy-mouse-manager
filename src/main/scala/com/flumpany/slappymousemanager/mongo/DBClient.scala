package com.flumpany.slappymousemanager.mongo

import org.mongodb.scala._

object DBClient {

  val mongoClient: MongoClient = MongoClient("mongodb://localhost")

  val database: MongoDatabase = mongoClient.getDatabase("mydb")

  val collection: MongoCollection[Document] = database.getCollection("test");

  val document: Document = Document("name" -> "Jerry")

  val insertObservable: Observable[Completed] = collection.insertOne(document)

  collection.find().collect().subscribe((results: Seq[Document]) => println(s"Found: #${results.size}"))



def write(doc:String): Unit = {
  collection.insertOne(document)
}

  def read: Unit = {
    collection.find()
  println(read)
  }
















}




