//package io.yian.monads

// ref: https://gist.github.com/nenono/b53dd7ce41629071a2fa
// ref: http://eed3si9n.com/learning-scalaz/ja/Monad.html
// ref: http://d.hatena.ne.jp/papamitra/20101002/scala_monad

object Monads {

  // f:T => V
  def double(x:Int):Int = x + x
  def sqrt(x:Int):Int = Math.sqrt(x).toInt

  // 합성함수를 만들어 보자
  // f:T => V 과 g:V => U 가 있다면 이 두 함수를 함성을 하게 되면
  // 수학적으로 g.f라고 쓰여지고 g(f(x))와 같다.
  //
  // g(f(x)) == f:T=>V, g:V=>U
  //
  // 이를 프로그램으로 구성해본다.
  // .를 사용하는 대신에 o함수를 만들어서 사용해본다.
  //

  def o[T,V,U](f:T=>V, g:V=>U) = (x:T) => g(f(x))
  
  // o함수를 curried로 정의해본다.
  def o2[T,V,U](f:T=>V) = (g:V=>U) => (x:T) => g(f(x))

  def monadTest001:Unit = {
    val doubleThenSqrt = o(double, sqrt)
    println(doubleThenSqrt(8))

    val doubleThenSqrt2 = o2(double)(sqrt)(8)
    println(doubleThenSqrt2)

    val doubleThenSqrt3 = o2(double)(sqrt)
    println(doubleThenSqrt3(8))

    // 아래는 오류가 난다. doubleThenSqrt4(sqrt) 시점에서 double의 정의가 필요하기 때문이다.
    /*
    val doubleThenSqrt4 = o2(double)
    println(doubleThenSqrt4(sqrt)(8))
    */
  }

  def main(args:Array[String]) : Unit = {
    monadTest001
  }
}

