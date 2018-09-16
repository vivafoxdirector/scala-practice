// https://qiita.com/takudo/items/dca70d8b2a639a7663d9
// http://www.office-matsunaga.biz/scala/description.php?id=4
// http://tech-blog.tsukaby.com/archives/812
object OptionTest extends App {
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
      //p2 <- list 
    } yield {
      println(p)
    }

    //println(opt.getOrElse("none"))
    println(opt)
  }

  // 참조: https://qiita.com/takudo/items/dca70d8b2a639a7663d9
  def opt_Val:Unit = {
    val optIntVal : Option[Int] = Some(10)
    val optStrVal : Option[String] = Some("String")
    println(optIntVal)
    println(optStrVal)

    var strVal = optStrVal.getOrElse("")
    println(strVal)
  }

  // Option을 사용하는 경우 getOrElse를 정말로 많이 사용된다. 
  // 개선전 (문제는 getOrElse를 사용할 수 없다)
  def optIntToStr1:Unit = {
    val optIntVal : Option[Int] = Some(10)
    if (optIntVal.isDefined) {
      val strVal = optIntVal.get.toString
      //val strVal = optIntVal.map(_.toString).getOrElse("")
      println(strVal)
    }
  }

  def optIntToStr2:Unit = {
    val optIntVal : Option[Int] = Some(10)
    if (optIntVal.isDefined) {
      val strVal = optIntVal.map(_.toString).getOrElse("")
      println(strVal)
    }
  }

  import scala.collection.mutable.ListBuffer
  def optIntToStr3:Unit = {
    val optIntVal : Option[Int] = Some(10)
    val optStrValForConvert:Option[String] = optIntVal.map(_.toString)
    val strVal = optStrValForConvert.getOrElse("")
    println(strVal)
  }

  def optIntListToStr1:Unit = {
    val strList = List("1", "2", "3")
    val newIntListForConvert = ListBuffer.empty[Int]
    strList.foreach(str => newIntListForConvert += str.toInt)
    val newIntList = newIntListForConvert.toList
    println(newIntList)
  }

  def optIntListToStr2:Unit = {
    val strList = List("1", "2", "3")
    val intList = strList.map(_.toInt)
    println(intList)
  }

  //list_Range
  //for_foreach
  //for_for_foreach
  //for_map
  //for_for_flatmap
  opt_for
  //optIntToStr2
  //optIntToStr3
  //optIntListToStr1
  optIntListToStr2
}
