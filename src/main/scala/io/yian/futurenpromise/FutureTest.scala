<<<<<<< HEAD
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Try, Success, Failure}

// ref: https://qiita.com/mtoyoshi/items/08766629abea6eb8e4bc
object FutureTest {
  def futureTest001 = {
    val msg = "Hello"
    val f: Future[String] = Future {
      Thread.sleep(1000)
      msg * 5
    }

    println(f.isCompleted) // false
    val result1: Option[Try[String]] = f.value
    println(result1) // Node

    Thread.sleep(2000)

    println(f.isCompleted) // true
    val result2: Option[Try[String]] = f.value

    println(result2.get.get) // hellohellohellohello
  }

  def futureTest002 = {
    val msg = "hello"
    val f: Future[String] = Future {
      Thread.sleep(1000)
      msg * 5
    }

    val result: String = Await.result(f, Duration.Inf)
    println(result)
  }

  def futureTest003 = {
    val msg = "hello"
    val f: Future[String] = Future {
      Thread.sleep(1000)
      msg * 5
    }

    Await.ready(f, Duration.Inf)
    f.value.get match {
      case Success(msg) => println(msg)
      case Failure(ex) => println(ex.getMessage)
    }
  }

  def futureTest004 = {
    val msg = "hello"
    val f: Future[String] = Future {
      Thread.sleep(1000)
      msg * 2
    }
    f.onSuccess {
      case msg : String => println(msg)
    }
    f.onFailure {
      case t : Throwable => println(t.getMessage())
    }

    Await.ready(f, Duration.Inf)
    println("")
  }

  def main(args:Array[String]) = {
    futureTest001
    futureTest002
    futureTest003
    //futureTest004
  }
=======
package io.yian.futurenpromise

// ref: https://qiita.com/mtoyoshi/items/08766629abea6eb8e4bc
object FutureTest {

    def futureTest001 : Unit = {}

    def main(args :Array[String]) : Unit = {

    }
>>>>>>> 2703cda176cc42e70a544250351715e3beb15c6e
}
