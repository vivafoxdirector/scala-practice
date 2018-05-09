abstract class Engineer(name:String) {
  println("Engineer name= " + name)
  def this() = this("")
  def work:Unit
}

//class Programmer(name:String, val age:Int) extends Engineer {
class Programmer(name:String, val age:Int) extends Engineer(name) {
  //name = name1
  println("name = " + name)
  println("age = " + age)

  def work = println(s"$name($age) coding...")
}

object Main extends App {
  val p = new Programmer("Yian",43)
  p.work
}

