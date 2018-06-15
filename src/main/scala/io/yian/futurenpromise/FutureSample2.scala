import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

// https://dwango.github.io/scala_text/future-and-promise.html

object FutureSample2 extends App {
  val s = "Hello"
  val f:Future[String] = Future {
    Thread.sleep(1000)
    println(s"[ThreadName] In Future: ${Thread.currentThread.getName}")
    s + "future!"
  }

  f.foreach {
    case s: String => {
      println(s"[ThreadName] In Success: ${Thread.currentThread.getName}")
      println(s)
    }
  }

  println("isComplited? " +f.isCompleted)  // false
  Await.ready(f, 5000 millisecond)  // hello future!

  println(s"[ThreadName] In App: ${Thread.currentThread.getName}")
  println(f"isComplited? " +f.isCompleted)  // true
}
