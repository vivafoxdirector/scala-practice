import io.yian.patternmatch

object PatternMatch extends App {
  val x = 10
  x match {
    case 1 => println(s"x is $x")
    case 10 => println(s"x is $x")
    case _ => println(s"x is other number")
  }

  println("=========================");

  val a:Any = "AriAri"
  val res = a match {
    case i:Int => println("x = " + i.toString);1
    case s:String => println("x = " + s);2
    case _ => println("other");3
  }
  println(res)
  
  println("=========================");

  x match {
    case i:Int if i >= 10 => println("i >= 10")
    case _ => println("other")
  }
  println("=========================");

  val r = Range(1,10)
  r.foreach(h => {
    h match {
      case i:Int if h <= 5 => println(s"$h is less than 5")
      case i:Int if h > 5 => println(s"$h is great than 5")
      case _ => println(s"$h")
    }
  })
  println("=========================");

  def know(c:Any) = {
    c match {
      case x:Integer => println("Integer")
      case x:String => println("String")
      case _ => println("Other")
    }
  }

  def know(c:Class[_]) = {
    println(
      c match {
        case _ if c == classOf[String] => "String"
        case _ if c == classOf[Int] => "Int"
        case _ if c == classOf[Integer] => "Integer"
        case _ => "otheri"
      }
    )
  }
  
  val any = List("A","B","C","D","E",1,2,3,4,5)
  
  know(classOf[String])
  println("-------------------------")
  any.foreach(h => {
    know(h.getClass)
  })
  println("-------------------------")
  any.foreach(h => {
    know(h)
  })
  println("=========================");

  val l = List(1,2,3,4,5,6,7,8,9,0)

  def append(x:List[Int]):List[Int] = {
    x match {
      case h::tail => println(h);append(tail)
      case _ => List.empty[Int]
    }
  }

  append(l)
}
