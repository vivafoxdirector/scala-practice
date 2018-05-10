case class Attender(name:String, status: Attender.Status = Attender.Status.Maybe) {
  def attend = copy(status = Attender.Status.Going)
  def absent = copy(status = Attender.Status.NotGoing)
}

object Attender {
  sealed abstract class Status
  object Status {
    case object Going extends Status
    case object NotGoing extends Status
    case object Maybe extends Status
  }
}

object SealdEnumExam2 {
  def main(args:Array[String]) = {
    val attender = Attender("Yian AriAri")

    println(attender)
    println(attender.attend)
    println(attender.absent)
  }
}
