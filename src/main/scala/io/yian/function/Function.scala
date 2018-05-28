//import io.yian.function

// ref: http://www.atmarkit.co.jp/ait/articles/1204/05/news126_3.html
object Function extends App {
  // Function Object
  // (value name: type_name) => function body
  //
  def func0(x:Int, y:Int) = x+y
  println(func0(1, 2))

  val func1 = (x:Int, y:Int) => x + y
  println(func1(1, 2))
  println("---------------------------------");

  val func2: (Int, Int) => Int = (x:Int, y:Int) => x + y
  println(func2(1, 2))
  println("---------------------------------");

  // Function0 ~ Function22
  // Function2[param1, param2, return] = (param1:type, param2:type) =>{}
  val func3:Function2[Int, Int, Int] = (x:Int, y:Int) => {x + y}
  println(func3(1, 2))
  println("---------------------------------");

  // Placeholder syntax
  val func4: (Int, Int) => Int = _ + _
  println(func4(1,2))

  println("---------------------------------");

  val func5 = (_:Int) + (_:Int)
  println(func5(1,2))

  /* ERROR OCCURED
  val func4:Function0[Int, Int, Int] = (x:Int, y:Int) => x + y
  println(func4(1, 2))
  */
  println("============Curried================")

  def calc(f:(Int, Int) => Int, num: Int) : Int = f(num, num)
  calc((x, y) => x+ y, 10)
  val add = (x:Int, y:Int) => x + y
  println(calc(add, 10))

  println("==================================")

  // return object of function 
  //::higher-order function::
  def getFunc(str:String):(Int,Int) => String = (x:Int, y:Int) => str + (x + y)
  val f = getFunc("result is ")
  println(f(1,2))

  println("==================================")

  // Variadic function
  // Usage:
  // def function_name(param_name:type_name *) = function_body
  def showMessage(args: String*) = for(arg <- args) println(arg)
  showMessage()
  showMessage("Yian")
  showMessage("Yian", "AriAri")

  println("---------------------------------");
  val array = Array("Yian", "AriAri")
  // ERROR Occured
  // showMessage(array)

  showMessage(array: _*)

  println("==================================")

  // default param value
  def show(message:String = "hello", count:Int = 1) = {
    var i = 0
    while (i < count) {
      println(message)
      i += 1
    }
  }
  show()
  println("---------------------------------");
  show(count = 2)
  println("---------------------------------");
  show(message = "bye", count = 3)
  
  println("==================================")
  
  def add2(x:Int, y:Int):Int = x + y
  val func7 = add2 _

  val func8 = func7(_:Int, 5)
  println(func8(2))

  val func9:(Int) => Int = func7(_, 5)
  println(func9(2))

  println("==================================")
  println("============ Closure =============")

  def counter() = {
    var count = 0
    () => {
      count += 1
      count
    }
  }

  val c1 = counter()
  println(c1())
  println(c1())
  println(c1())
  
  println("-------------Fail-----------------");

  def counter2:(Int)=>Int = {
    var count = 0
    (m:Int) => {
      count += 1
      count += m
      count
    }
  }

  val c2 = counter2(_)

  println(c2(2))
  println(c2(2))
  println(c2(2))
  
  println("------------Fail------------------");

  def counter3()(m:Int) = {
    var count = 0
    () => {
      count += 1
      count
    }
  }

  val c3 = counter3()(_)
  println(c3(1))

  println("------------Success------------------");

  def counter4() = {
    var count = 0
    (m:Int) => {
      count += 1
      count += m
      count
    }
  }

  val c4 = counter4()
  println(c4(1))
  println(c4(1))
  println(c4(1))

  println("---------------------------------");

  def printOverlength(s:String) = {
    val len = s.length
    (xs:List[String]) => {
      for (e <- xs; if e.length > len ) {
        println(e + ":" + (e.length - len))
      }
    }
  }

  val printOverFoo = printOverlength("AriAri")
  printOverFoo(List("Bar", "YianYian", "Foo", "HogeHoge"))

  println("==================================")
  println("========== local function ===========")

  def showLanguage(title:String, langList:List[String]) : Unit = {
    def printLang(item:String) = {
      println(title + ":" + item)
    }

    for (lang <- langList) printLang(lang)
  }

  showLanguage("Programming Language", List("Java", "Scala", "Ruby"))
}
