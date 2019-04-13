object ExamSel {
  def exam01 : Unit = {
    println("exam01")
  }

  def main(args: Array[String]) : Unit = {
    val a = new ExamFactory("lazy")
    a.addFunc("lazy001", exam01)

    //val b = Person("Yian", exam01)
    //println(b.name)

    //val sel = scala.io.StdIn.readLine()
  }
}

class Person(name:String, f: => Unit)
class ExamFactory(name: String) {
  var factory = List[ExamElement]()
  def addFunc(name:String, f: => Unit) {
    factory = new ExamElement(name, f) :: factory
  }
}
class ExamElement(name: String, f: => Unit) {
  def play : Unit = {
    f
  }
}
