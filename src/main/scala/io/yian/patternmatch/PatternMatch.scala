//package io.yian.patternmatch
// ref: http://www.ne.jp/asahi/hishidama/home/tech/scala/match.html
// ref: https://dwango.github.io/scala_text/control-syntax.html
// ref: https://qiita.com/techno-tanoC/items/3dd3ed63d161c53f2d89
// ref: https://codezine.jp/article/detail/5251
// ref: https://dwango.github.io/scala_text/case-class-and-pattern-matching.html
object PatternMatch {

  def patternMatchTest01 = {
    val x = 10
    x match {
      case 1 => println(s"x is $x")
      case 10 => println(s"x is $x")
      case _ => println(s"x is other number")
    }
  }

  def patternMatchTest02 = {
    val a:Any = "AriAri"
    val res = a match {
      case i:Int => println("x = " + i.toString);
      case s:String => println("x = " + s);
      case _ => println("other");
    }
    println(res)
  }

  def patternMatchTest03 = {
    val x = 10
    x match {
      case i:Int if i >= 10 => println("i >= 10")
      case _ => println("other")
    }
  }

  def patternMatchTest04 = {
    val r = Range(1,10)
    r.foreach(h => {
      h match {
        case i:Int if h <= 5 => println(s"$h is less than 5")
        case i:Int if h > 5 => println(s"$h is great than 5")
        case _ => println(s"$h")
      }
    })
  }

  def patternMatchTest05 = {
    def knowAny(c:Any) = {
      c match {
        case x:Integer => println("Integer")
        case x:String => println("String")
        case _ => println("Other")
      }
    }

    def knowPrint(c:Class[_]) = {
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
    
    knowAny(classOf[String])
    any.foreach(h => {
      knowAny(h.getClass)
    })
    
    any.foreach(h => {
      println("-------------------------")
      knowPrint(h.getClass)
    })
  }

  def patternMatchTest06 = {
    def append(x:List[Int]):List[Int] = {
      x match {
        case h::tail => println(h);append(tail)
        case _ => List.empty[Int]
      }
    }
    val l = List(1,2,3,4,5,6,7,8,9,0)
    append(l)
  }

  def patternMatchTest07 = {
    def p(list:List[String]):Unit = {
      list match {
        case (head::Nil) => println(head)
        case (head::tail) => {
          println(head)
          p(tail)
        }
        case _ =>
      }
    }
    p(List("a","b","c"))
  }

  def main(args:Array[String]) = {
    patternMatchTest01
    patternMatchTest05
    patternMatchTest07
  }

}
