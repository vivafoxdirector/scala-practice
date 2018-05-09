//package io.yian.`object`

class Programmer(val language:String) {
  println("Created Programmer Instance");
  println("Language = " + language)
  
  require(language != null)

  def this() = this("Scala")
  def coding() = println(language + ".. coding..")
}

object SampleUtil {
  def hello() = println("AriAri")
}

//---------------------------------------

class SampleCompanion private (num:Int, id:Int)

object SampleCompanion {
  def apply(num:Int) = {
    println("apply : " + this)
    new SampleCompanion(num, 0)
  }
}

//---------------------------------------
// Object Pattern Match need unapply
//class Apple
object Apple {
  /*
  def unapply(a: Any):Boolean = {
    if (a.isInstanceOf[Apple]) true else false
  }*/
}
object Mikan {
}

class Orange(val name:String)
object Orange {
  def apply(name:String):Orange = new Orange(name)
  def unapply(a:Orange):Option[String] = {
    Some(a.name)
  }
}

//---------------------------------------
// Case Class use instead unapply constructor method
case class Ringo(name:String)
case class Abokado(name:String)

object Main extends App {
  val p = new Programmer("Scala");
  p.coding()
  // Occured IllegalException...
  //val p = new Programmer(null)

  println("===================================");
  println("== SingletonObject in Scala");

  SampleUtil.hello()
  
  println("===================================");
  println("== Companion Object");
  
  // SampleCompanion.apply(10) ==> SampleCompanion(10) 
  val ins = SampleCompanion(10)
  println(ins)

  val ins2 = SampleCompanion.apply(10)
  println(ins2)

  println("===================================");
  println("== Option");

  val m = Map("Scala"->1, "Java"->2)
  println(m.get("Scala")) // if value of key existed print Some
  println(m.get("Ruby"))  // if value of key not existed print None
  
  println("-- print value")
  println(m.get("Scala").get)

  // occured exception
  //println(m.get("Ruby").get)

  println(m.get("Ruby").getOrElse("Nothing"))

  println("-----------------------------------")
  println("-- Option used by PatternMatch");

  def testMatch(opt:Option[Int]) = {
    opt match {
      case Some(n) => println(n)
      case None => println("None!")
    }
  }

  testMatch(Some(10))
  testMatch(None)

  println("-----------------------------------")
  println("-- Option used by PatternMatch with Unapply");

  def matchTest(value:Any) = {
    value match {
      case Apple => println("Apple")
      case Mikan => println("Mikan")
      case Orange("DEKOPON") => println("Orage.name=DEKOPON")
      case Ringo(name) => println("Apple.name="+name)
      case Abokado("DEKOPON") => println("ABOKADO")
      case _ => println("other")
    }
  }

  matchTest(Orange("something"))
  matchTest(Orange("DEKOPON"))

  matchTest(Apple)
  matchTest(Mikan)
  matchTest("hello")

  println("-----------------------------------")
  println("-- pattern match used case class intead unapply");

  matchTest(Ringo("Ringo"))
  matchTest(Abokado("DEKOPON"))
}
