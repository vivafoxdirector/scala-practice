//package io.yian.monads

// ref: https://gist.github.com/nenono/b53dd7ce41629071a2fa
// ref: http://eed3si9n.com/learning-scalaz/ja/Monad.html
// ref: http://d.hatena.ne.jp/papamitra/20101002/scala_monad

// ref: https://github.com/enshahar/BasicFPinScala/blob/master/Intermediate/Monad.md
object Monads {

  // 스칼라에서는 어떤 값을 감싼 타입을 만드는 가장 쉬운 방법은 케이스 클래스를
  // 활용 하는 것이다.
  // 아래 코드는 단순히 T타입의 값을 감싼 타입인 Boxed[T] 로 바꿔주는 케이스 클래스를 정의 한다.
  case class Boxed[T](value:T)

  // 조금 더 유용한 클래스를 만들자면 로그를 남기는 Logged[T] 를 들 수 있다.
  // 값의 변화를 추적하면서 리스트에 로그를 남길 때 사용한다.
  case class Logged[T](value:T, log:List[String])

  abstract class MyOption[+A]
  case class MySome[+A](x: A) extends MyOption[A]
  case object MyNone extends MyOption[Nothing]

  class Lazy[T](value: () => T) {
    def getValue():T = value()
  }

  abstract class MyList[+A]
  case class Cons[B](var hd: B, var tl: MyList[B]) extends MyList[B]
  case object MyNil extends MyList[Nothing]

  // Int => C[Int] 생성 함수들
  // 케이스 클래스인 경우 기본 제공되는 클래스 짝 객체에 있는 생성자함수를 사용 가능하며,
  // 아닌 경우 new 를 사용하자.
  def initBoxed(x:Int):Boxed[Int] = Boxed(x)
  def initLogged(x:Int):Logged[Int] = Logged(x, List())
  def initMyOption(x:Int):MyOption[Int] = MySome(x)
  def initLazy(x: => Int):Lazy[Int] = new Lazy(() => x)
  def initMyList(x:Int):MyList[Int] = Cons(x, MyNil)

  def monadTest000:Unit = {
    val x1:MyList[Int] = Cons(1, Cons(2, Cons(8, MyNil)))
    println("x1: " + x1)
  }

  // ============================================================

  // f:T => V
  def double(x:Int):Int = x + x
  def sqrt(x:Int):Int = Math.sqrt(x).toInt

  // 어떤 정수에 대해 이 함수들을 적용하고, 그 값을 앞에서 정의한 클래스 C로 감싸고 싶다. 물론 감쌀때는 클래스의 의미에 맞게 적절한 방식으로 감싸야만 한다.

  // Boxed의 경우는 그냥 감쌀뿐 특별히 하는 일은 없다.
  def doubleBoxed(x:Int):Boxed[Int] = Boxed(x + x)
  def sqrtBoxed(x:Int):Boxed[Int] = Boxed(Math.sqrt(x).toInt)

  // Logged의 경우 각 함수에 맞게 로그를 덧붙이면 될 것이다.
  // case class Logged[T](value:T, log:List[String])
  def doubleLogged(x:Int):Logged[Int] = Logged(x + x, List("double("+x+") = " + (x + x)))
  def sqrtLogged(x:Int):Logged[Int] = Logged(Math.sqrt(x).toInt, List("sqrt("+x+").toInt= " + Math.sqrt(x).toInt))

  // MyOption 의 경우 각 함수의 성질에 따라 오류라면 MyNone, 아니라면 MySome을 반환한다.
  //  abstract class MyOption[+A]
  //  case class MySome[+A](x: A) extends MyOption[A]
  //  case object MyNone extends MyOption[Nothing]
  def doubleMyOption(x:Int):MyOption[Int] = MySome(x + x)
  def sqrtMyOption(x:Int):MyOption[Int] = if(x >= 0) MySome(Math.sqrt(x).toInt) else MyNone

  // Lazy는 바로 계산을 하지 않고 함수를 만들어서 나중에 필요할 때 계산을 수행하게 만든다(수행시점은 확인하기 위해 간단하게 프린트문을 추가했다.) 이때 정수 연산을 미뤄둔다. 안그러면 식을 인자로 주는 경우 해당 식이 평가되어 버린다.
  // class Lazy[T](value: () => T) {
  //   def getValue():T = value()
  // }

  def doubleLazy(x: => Int):Lazy[Int] = new Lazy(() => {
    print("lazy double(" + x + ") run\n")
    x + x
  })
  def sqrtLazy(x: => Int):Lazy[Int] = new Lazy(() => {
    print("lazy sqrt(" + x + ") run\n")
    Math.sqrt(x).toInt
  })

  // MyList 는 계산 결과만을 유일한 원소로 포함하는 리스트로 만든다.
  //  abstract class MyList[+A]
  //  case class Cons[B](var hd: B, var tl: MyList[B]) extends MyList[B]
  //  case object MyNil extends MyList[Nothing]
  def doubleMyList(x:Int):MyList[Int] = Cons(x + x, MyNil)
  def sqrtMyList(x:Int):MyList[Int] = Cons(Math.sqrt(x).toInt, MyNil)

  // ===============================================================

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

  def monadTest002:Unit = {
    o(doubleBoxed, sqrtBoxed)
  }

  def main(args:Array[String]) : Unit = {
    monadTest000
    monadTest001
    monadTest002
  }
}