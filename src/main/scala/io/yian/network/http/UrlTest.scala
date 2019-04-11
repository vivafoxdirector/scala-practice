package io.yian.network.http

import scalaj.http.{Http, HttpResponse}

// ref: https://qiita.com/nekonoprotocol/items/0857b56ba720c853ee62

// scalaj test
object Filename {
  def unapply(url: String):Option[String] = {
    url.split("/").reverse.toList.headOption
  }
}

object UrlTest extends App {
  //def main(args: Array[String]) : Unit = {
    println("test start")
    var url = "saved image"

    val response: HttpResponse[String] = Http("http://api.hostip.info/get_json.php?ip=8.8.8.8").param("q","monkeys").asString

    //val data = Resource.fromURL(url).bytearray
    println("downloading: %s ... " format url)
    println(response.body)
//    url match {
//      case Filename(file) => Resource.fromFile(new java.io.File("data", file)).write(data)
//      case _ => sys.error("Oops!")
//    }
    println("test end")
  //}
}
