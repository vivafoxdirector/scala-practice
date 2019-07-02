import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

// ref: https://dwango.github.io/scala_text/future-and-promise.html
// ref: https://qiita.com/mtoyoshi/items/08766629abea6eb8e4bc

object FutureSample extends App {
  val s = "Hello"
  val f:Future[String] = Future {
    Thread.sleep(1000)
    s + "future!"
  }

  f.foreach {
    case s: String => println(s)
  }

  println(f.isCompleted)  // false
  Thread.sleep(5000)      // hello future!
  println(f.isCompleted)  // true

  val f2: Future[String] = Future {
    Thread.sleep(1000)
    throw new RuntimeException("일부러 그럼")
  }

  f2.failed.foreach {
    case e: Throwable => 
      println(e.getMessage)
  }

  println(f2.isCompleted) // false
  Thread.sleep(5000)      // 일부러 그럼
  println(f2.isCompleted) // true
}
