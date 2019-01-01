package com.flumpany.slappymousemanager

import io.finch._
import org.scalatest.{FunSpec, Matchers}

class MainTest extends FunSpec with Matchers {

  val username = "test1"
  describe("healthcheck") {
    it("should return OK when the root endpoint is hit") {
      Main.healthcheck(Input.get("/")).awaitValueUnsafe().get should not be "NotOK"
      Main.healthcheck(Input.get("/")).awaitValueUnsafe().get shouldBe "OK"
    }
  }

  describe(s"userData/$username"){
    it(s"should return $username when the endpoint /userData/$username is hit") {
      Main.userData(Input.get(s"/userData/$username")).awaitValueUnsafe().get shouldBe username
    }
  }
}