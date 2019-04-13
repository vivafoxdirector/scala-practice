package io.yian.common

object ExamTest {
  def exam01 : Unit = {
    println("exam01")
  }
  def exam02 : Unit = {
    println("exam02")
  }
  def exam03 : Unit = {
    println("exam03")
  }
  def exam04 : Int = {
    1 + 2
  }

  def main(args: Array[String]) : Unit = {
    val a = new ExamHolder("lazy")
    a.addFunc("lazy001", exam01)
    a.addFunc("lazy002", exam02)
    a.addFunc("lazy003", exam03)
    a.addFunc("lazy004", println(exam04))
    a.selectFunc
  }
}
