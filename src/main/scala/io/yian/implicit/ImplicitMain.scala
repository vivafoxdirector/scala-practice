package io.yian.`implicit`

// ImplicitClass는 직접 생성하지 않고 예제와 같이
// object안에서 생성이 가능하다.
// 그리고 implicit class 생성자는 단 하나의 인수만을 가지고
// 이 인수가 변환 대상이 된다. 인수가 복수개가 될수 없는 이유이기도 한다(복수개를 변환할 수 없다)
// ref: https://qiita.com/miyatin0212/items/f70cf68e89e4367fcf2e
// ref:
object ImplicitMain extends App{
    class SuperInt (val i: Int) {
        def square = i * i
    }

    implicit def intToSuper(i:Int): SuperInt = new SuperInt(i);

    println(1000.square)
    println("1000".toInt)
    //============================================
    implicit class TimeInt(i : Int) {
        def days = i * 1000 * 60 * 60 * 24
        def hours = i * 1000 * 60 * 60
        def minutes = i * 1000 * 60
        def seconds = i * 1000
    }

    println("3초간 기다림")
    Thread.sleep(3.seconds)
    println("OK")
    //============================================
    implicit val defaultInt:Int = 1000
    implicit val defaultString:String = "DEFAULT"

    def defaultIntFunc(implicit x:Int):Int = x * x
    def defaultStringFunc(implicit z:String): String = s"$z OK!"
    def defaultAll(implicit x:Int, z:String) : String = s"$x  $z"
    def defFunc(x : Int = 1000) : Int = x * x
    println(defaultIntFunc)
    println(defaultStringFunc)
    println(defaultAll)
    println(defFunc(10))
    println(defFunc())
    //============================================
    implicit val defaultList = List(1,2,3)
    def curryFunc(x:Int, s:String)(z:Float)(implicit xs:List[Int]):String = (s * x) + z + xs.sum
    println(curryFunc(1, "Hoge")(0.3f))
}