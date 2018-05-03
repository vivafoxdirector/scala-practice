import io.yian.function

// ref: http://www.atmarkit.co.jp/ait/articles/1204/05/news126_3.html
object Function extends App {
  // Function Object
  // (value name: type_name) => function body
  //
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
  println("==================================")

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

}
