package io.yian.dummygen

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString

import java.nio.file.Paths
import java.io.IOException

// ref: http://fits.hatenablog.com/entry/2017/01/31/010719
object SampleApp extends App {
  implicit val system = ActorSystem()
  import system.dispatcher  // ExecutionCont 를 implicit
  implicit val materializer = ActorMaterializer()

  // 파일에 쓰기 sample1.txt에 sample data를 출력
  val res1 = Source.single("sample data")
  .map(ByteString.fromString)
  .runWith(FileIO.toPath(Paths.get("sample1.txt")))

  // 파일 읽기 sample2.txt 내용을 println
  val res2 = FileIO.fromPath(Paths.get("sample2.txt"))
  .map(_.utf8String)
  .recover{
    case e: IOException => s"invalid file, ${e}"
  }
  . runForeach(println)

  res1.flatMap(_=> res2).onComplete(_ => system.terminate)
}

