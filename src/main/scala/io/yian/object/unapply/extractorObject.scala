object extractorObject {
  object Sample {
    def apply(str:String):String = {
      str + " (apply called"
    }

    def unapply(str:String):Option[String] = {
      if (str.nonEmpty) Some(str + (" (unapply called)")) else None
    }
  }

  class Apple
  object Apple {
    def unapply(obj:Any) : Option[String] = {
      if (obj.isInstanceOf[Apple]) Some("unapply called") else None
    }
  }

  def main(args:Array[String]):Unit = {
    val p = Sample("AriAri")
    //val p = Apple
    println(p)

    p match {
      //case Apple => println("Apple")
      case Sample(s) => println(s)
      case _ => println("No match")
    }
  }
}

