package com.flumpany.slappymousemanager

import io.finch._
import org.scalatest.{FunSuite, Matchers}

class MainTest extends FunSuite with Matchers {
  test("healthcheck") {
    assert(Main.healthcheck(Input.get("/")).awaitValueUnsafe().contains("OK"))
  }

  test("helloWorld") {
    assert(Main.helloWorld(Input.get("/hello")).awaitValueUnsafe().contains(Main.Message("World")))
  }

  test("hello") {
    assert(Main.hello(Input.get("/hello/foo")).awaitValueUnsafe().contains(Main.Message("foo")))
  }

  test("whatever"){

    Main.hello(Input.get("/hello/floo")).awaitValueUnsafe() should not contain Main.Message("floo")

  }
}