package com.flumpany.slappymousemanager

import cats.effect.IO
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._
import io.circe.generic.auto._

object Main extends App {

  case class Message(hello: String)

  def healthcheck: Endpoint[IO, String] = get(pathEmpty) {
    Ok("OK")
  }



  def userData: Endpoint[IO, String] = get("userData" :: path[String]) { username: String =>
    Ok(username)
  }




  def service: Service[Request, Response] = Bootstrap
    .serve[Text.Plain](healthcheck)
    .serve[Application.Json](userData)
    .toService

  Await.ready(Http.server.serve(":8081", service))
}