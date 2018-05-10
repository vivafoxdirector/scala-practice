sealed abstract class Person
case object Programmer extends Person
case object Editor extends Person
case object Designer extends Person

object SealdEnumExam1 {
  def main(args:Array[String]) = {
    var p : Person = Editor
    (p: @unchecked) match {
      case Programmer => println("Programmer")
      case Editor => println("Editor")
    }
  }
}

