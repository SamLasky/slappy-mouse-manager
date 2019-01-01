package com.flumpany.slappymousemanager.mongo

import org.bson.conversions.Bson
import org.mongodb.scala._
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromRegistries, fromProviders}

import scala.concurrent.Future

class DBClient(url: String, databaseName: String, collectionName: String) {

  val mongoClient: MongoClient = MongoClient(url)
  val codecRegistry = fromRegistries(fromProviders(classOf[UserData]), DEFAULT_CODEC_REGISTRY )

  val database: MongoDatabase = mongoClient.getDatabase(databaseName).withCodecRegistry(codecRegistry)

  val collection: MongoCollection[UserData] = database.getCollection(collectionName)

  def create(newDoc:UserData): Future[Completed] = {
    collection.insertOne(newDoc).toFuture()
  }

  def read(condition: Bson): Future[Seq[UserData]] = {
    collection.find[UserData](condition).toFuture()
  }
}

case class UserData(name: String)