// ref: https://gist.github.com/nenono/2523c406735b11ee9b46
object CurriedFunction2 {
  def add(x:Int, y:Int, z:Int) = x + y + z
  def sum(a:Int)(b:Int)(c:Int) = a + b + c
  def sumCurry(a:Int) = (b:Int) => (c:Int) => a + b +c

  def curryTest1 = {
    println(sum(1)(2)(3))
    println(sumCurry(1))

  }

  def curryTest2 = {
    // def로 정의한 메소드는 커리화할 수 없기 때문에 "스페이스 + _"을 이용하여 함수 오브젝트를 취득한다.
    val addFunc = add _
    // add _ 라는 것은 add(_,_,_)와 같다

    val a = addFunc.curried
    val a1 = a(1)
    val a2 = a1(1)
    val a3 = a2(1)

    println(s"result: ${a1}, ${a2}, ${a3}")
    // StringContext클래스의 메소드호출로 변환됨.
    // 이경우 new StringContext("result: ", ", ", ", ").s(a1, a2, a3) 가 됨

    val a4 = (add _ curried)(1)(1)(1)
    println(s"result: ${a4}")
  }

  def main(args: Array[String]) {
    curryTest1
    //curryTest2
  }
}
