object LazyTest extends App {
  def getJSON:String = {
    println("requesting...")
    val result = scala.io.Source.fromURL("http://api.hostip.info/get_json.php?ip=8.8.8.8").mkString
    println("request done")
    result
  }

  //list_Range
  //for_foreach
  //for_for_foreach
  //for_map
  //for_for_flatmap
  //
  // opt_for2
  println(getJSON)
  println(getJSON)
}
