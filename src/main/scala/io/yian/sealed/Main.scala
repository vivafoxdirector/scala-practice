package io.yian.`sealed`
// ref : https://gist.github.com/gakuzzzz/10081860
//-----------------------------------------
// sealed is useful when pattern match
sealed abstract class Engineer
case class Programmer() extends Engineer
case class Tester() extends Engineer
case class Architect() extends Engineer

//-----------------------------------------
// sealed also useful enum
sealed trait UserType {
  val label: String
}

object UserType {
  case object Admin extends UserType {
    val label = "Manager"
  }
  case object Regular extends UserType {
    val label = "Regular"
  }
  case object Guest extends UserType {
    val label = "Guest"
  }
}

//----------------------------------------
sealed abstract class UserType2(val label:String)
object UserType2 {
  case object Admin extends UserType2("Manager")
  case object Regular extends UserType2("Regular")
  case object Guest extends UserType2("Guest")
}

object Main {
  def main(args:Array[String]) = {
    val e:Engineer = new Programmer
    (e: @unchecked) match {
      case p:Programmer => println("Programmer")
      case t:Tester => println("Tester")
    }

    println(UserType)
    println(UserType.Admin)
    println(UserType.Admin.label)

    println(UserType2)
    println(UserType2.Admin)
    println(UserType2.Admin.label)
  }
}
