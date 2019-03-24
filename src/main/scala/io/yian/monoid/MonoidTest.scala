package io.yian.monoid

// ref: https://gist.github.com/takuya71/3760513
object MonoidTest {
  def monoidTest01 = {
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
  }
  //============================================
  def main(args:Array[String]): Unit = {
    monoidTest01
  }
}
