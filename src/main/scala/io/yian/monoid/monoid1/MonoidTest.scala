package io.yian.monoid.monoid1

// ref: https://gist.github.com/takuya71/3760513
// 사전 지식은 foldLeft, foldRight
object MonoidTest {

  def monoidTest01 = {
    def sum(xs: List[Int]):Int = xs.foldLeft(0) {(a, b) => a+b}
    def p(a: Any) {println("> " + a)}

    println
    p(sum(List(1,2,3,4,5)))
    println
  }
  //============================================
  def monoidTest02 = {
    object IntMonoid {
      def mappend(a:Int, b:Int):Int = a + b
      def mzero : Int = 0
    }
    def sum(xs:List[Int]): Int = xs.foldLeft(IntMonoid.mzero)(IntMonoid.mappend)
    def p(a:Any): Unit = {
      println("> " + a)
    }

    println
    p(sum(List(1,2,3,4,5)))
    println
  }
  //============================================
  def main(args:Array[String]): Unit = {
    monoidTest01
    //monoidTest02
  }
}
