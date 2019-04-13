import io.yian.common.ExamTest.exam01
import io.yian.common.ExamHolder

// ref: https://qiita.com/suin/items/2780a9b7d47a14f71e24
object LazyValTest {

  //============ Compare not use lazy val and use lazy val ========================
  def getJSON_asis:String = {
    println("requesting...")
    //val result = scala.io.Source.fromURL("http://api.hostip.info/get_json.php?ip=8.8.8.8").mkString
    val result = scala.io.Source.fromURL("https://codezine.jp/article/detail/5329", "utf-8").mkString
    println("request done")
    result
  }

  // use 'lazy val'
  lazy val getJSON_tobe:String = {
    println("requesting...")
    //val result = scala.io.Source.fromURL("http://api.hostip.info/get_json.php?ip=8.8.8.8").mkString
    val result = scala.io.Source.fromURL("https://codezine.jp/article/detail/5329", "utf-8").mkString
    println("request done")
    result
  }

  def lazyValTest001A:Unit = {
    println(getJSON_asis) // 호출
    println(getJSON_asis) // 호출 
  }
  
  def lazyValTest001B:Unit = {
    println(getJSON_tobe) // 호출
    println(getJSON_tobe)
  }
  //===============================================================================

  def testBefore(judgeOpt: Option[Int]) = {
    val numberList = Seq(1,2,3,4,5,6,7,8,9,10)

    val (leftList, rightList) = numberList.partition {
      n => 
        if (judgeOpt.isEmpty) {
          false 
        } else if (judgeOpt.get > numberList.length) {
          true
        } else {
          if (n % judgeOpt.get == 0) {
            false
          } else {
            true
          }
        }
    }
    println("left: " + leftList)
    println("right: " + rightList)
  }

  def testAfter(judgeOpt: Option[Int]) = {
    val numberList = Seq(1,2,3,4,5,6,7,8,9,10)

    def judgeFunction(judgeOpt: Option[Int], list: Seq[Int]):(Int) => Boolean = {
      if (judgeOpt.isEmpty) {
        println("None")
        (_: Int) => false
      } else if (judgeOpt.get > list.length) {
        println("Over")
        (_: Int) => true
      } else {
        divideFunction(_:Int, judgeOpt.get)
      }
    }

    def divideFunction(target:Int, input:Int):Boolean = {
      if (target % input == 0) {
        false
      } else {
        true
      }
    }

    lazy val f = judgeFunction(judgeOpt, numberList)
    val (leftList, rightList) = numberList.partition {
      n =>
        f(n)
    }
    println(leftList)
  }

  def lazyValTest002A:Unit = {
    testBefore(Some(3))
  }
  
  def lazyValTest002B:Unit = {
    testBefore(Some(3))
  }

  def main(args:Array[String]) : Unit = {
    val a = new ExamHolder("lazy")
    a.addFunc("lazyValTest001A", "Not use lazy val example", lazyValTest001A)
    a.addFunc("lazyValTest001B", "Use lazy val example", lazyValTest001B)
    a.addFunc("lazyValTest002A", "Not use lazy val example", lazyValTest002A)
    a.addFunc("lazyValTest002B", "Use lazy val example", lazyValTest002B)
    a.selectFunc
  }
}
