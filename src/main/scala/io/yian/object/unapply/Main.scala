// ref: https://qiita.com/skta/items/aef20515f3bdadfb5ad8
// ref: https://qiita.com/yotsak83/items/d3c1495d83690dd725ef
class Test private (val name:String)
object Test {
  def apply(str:String) = {
    new Test(str)
  }
  def unapply(obj:Any) = {
    if(obj.isInstanceOf[Test]) {
      Some(obj.asInstanceOf[Test].name)
    } else {
      None
    }
  }
}

object Main {
  def main(args: Array[String]) : Unit = {
    match1(Test("AriAri"))
    match2(Test("OriOri"))

  }

  def match1(obj:Any) = {
    obj match {
      case Test(name) => println("..." + name)
      case _ => println("other")
    }
  }

  def match2(obj: Any) = {
    obj match {
      case Test("AriAri") => println("matched...")
      case _=> println("other")
    }
  }
}
