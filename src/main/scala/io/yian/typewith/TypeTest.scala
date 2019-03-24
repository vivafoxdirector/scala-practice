package io.yian.typewith

import java.text.SimpleDateFormat

// ref: https://www.atmarkit.co.jp/ait/articles/1208/24/news138.html
// ref: https://qiita.com/f81@github/items/7a664df8f4b87d86e5d8
object TypeTest {
  //===========================================================
  class Foo {
    def exec = println("Foo#exec실행")
  }
  abstract class Base {
    type ShomethingFoo <: Foo   // Foo이거나 Foo를 계승한것으로 제약한다.
    def show(something:ShomethingFoo)
  }
  class Ex1 extends Base {
    // override 키워드를 붙인다. amm에서는 없어도 동작된다.?????
    override type ShomethingFoo = Foo
    override def show(something: ShomethingFoo): Unit = something.exec
  }
  def typeTest01 = {
    val x = new Ex1
    x.show(new Foo)
  }
  //============================================
  type X = List[Int]
  def typeTest02(x:X) : Unit = {
    x.foreach(i=>println(i))
  }
  //============================================
  class TypeParam[T](val t: T) {
    def get: T = this.t
  }
  def typeTest03 = {
    val stringTypeParam = new TypeParam[String]("test")
    println(stringTypeParam.get)

    val intTypeParam = new TypeParam[Int](1)
    println(intTypeParam.get)
  }
  //============================================
  def main(args:Array[String]): Unit = {
    typeTest01
    typeTest02(List(1,2,3,4,5))
    typeTest03
  }
}
