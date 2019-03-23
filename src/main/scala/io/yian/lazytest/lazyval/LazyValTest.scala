package io.yian.lazytest.lazyval

object LazyValTest extends App {
  def getJSON = {
    println("requesting...")
    val result = scala.io.Source.fromURL("http://api.hostip.info/get_json.php?ip=8.8.8.8", "utf-8").mkString
    println("request done")
    result
  }

  println(getJSON)
  println(getJSON)
  println(getJSON)
}
