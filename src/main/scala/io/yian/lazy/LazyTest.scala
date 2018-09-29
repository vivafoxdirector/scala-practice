object LazyTest extends App {
  def list_Range:Unit = {
    val list1 = List("1","2","3","4","5")

    // 1부터 6미만 까지
    val list2 = Range(1,6).toList

    // 1부터 6까지
    val list3 = (1 to 6).toList

    println("List(\"1\"..): " + list1)
    println("Range(1,6): "+ list2)
    println("(1 to 6): " + list3)

    println("moto: " + list1)
    println("tail: " + list1.tail)
    println("head: " + list1.head)
    println("last: " + list1.last)
    println("init: " + list1.init)
  }

  def for_foreach:Unit = {
    val list = List("123","234","345","456","567")

    // for loop
    for (a <- list) {
      println(a)
    }

    // 아래의 코드는 위와 같다.
    list.foreach(p => println(p))
  }

  def for_for_foreach:Unit = {
    val list = List("123","234","345","456","567")
    
    for {
      a <- list
      c <- a
    } println(c)

    // 아래의 코드는 위와 같다.
    list.foreach(p => p.foreach(c => println(c)))
  }

  def for_map:Unit = {
    val list = List("123","234","345","456","567")
    for (p <- list) yield(println(p))
    list.map(p=>println(p))
  }

  def for_for_flatmap:Unit = {
    val list = List("123","234","345","456","567")

    for {
      p <- list
      c <- p
    } yield(println(c))
    println("----")

    list.flatMap(p => for {c <- p} yield(println(c)))
    println("----")
    list.flatMap(p => p.map(c => println(c)))
  }

  def opt_for:Unit = {
    val a = Some(1)
    val b = Some("abc")
    val c = Some(0.5D)

    for {
      x <- a
      y <- b
      z <- c
    } {
      println(s"$x, $y, $z")
    }

    val opt = for {
      x <- a
      y <- b
      z <- c
    } yield {
      s"$x, $y, $z"
    }

    println(opt.getOrElse("none"))
  }

  def opt_for2:Unit = {
    val list = Range(1,6).toList

    val opt = for {
      p <- list
      p2 <- list
    } yield {
      s"$p, $p2"
    }

    //println(opt.getOrElse("none"))
  }

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
