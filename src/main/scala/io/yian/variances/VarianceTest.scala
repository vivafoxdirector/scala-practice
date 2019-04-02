package io.yian.variances


object VarianceTest {

  // ref: http://takafumi-s.hatenablog.com/entry/2015/08/20/122350
  def typeAnnotationTest1 = {
    class Foo(foo:AnyRef){}

    val f1 = new Foo("anyref":AnyRef)
    val f2 = new Foo("string":String)

    // *에러*
    // AnyRef 서브 타입이 아닌것은 에러가 난다.
    //val f3 = new Foo(1:Int)
  }

  def typeAnnotationTest2:Unit = {
    class Item[T](elem:T){}

    // T -> AnyRef
    class Bar(item:Item[AnyRef]) {
      def show = {
        this.item
      }
    }
    new Bar(new Item[AnyRef]("anyref"))

    // *에러* '비변' 이라고 한다.
    // Item[String] -> Item[AnyRef]
    // new Bar(new Item[String]("string"))

    // String은 암묵적으로 AnyRef로 변환된다.
    val b = new Bar(new Item("string"))
    println(b.show)

    // * 에러 *
    // Int는 Anyref로 암묵적으로 변환할 수 없다.
    //val i = new Bar(new Item(1))
  }

  def typeAnnotationTest3 : Unit = {
    // typeAnnotationTest1, typeAnnotationTest2 를 참고로 하여
    // 변위 어노테이션에 대하여 설명한다.
    // Type파라메타가 있을때 Type파라메타에 명시적으로 서브형 타입 관계를
    // 부여하고 싶을때.. 결국 아래와 같은 선언을 하고 싶은 경우이다.
    //
    // Item[String] -> Item[AnyRef]
    //
    // 이것을 Scala에서는 "공변" 이라고 한다.(공변은 리턴값으로 사용한다)

    class Item2[+T](elem: T) {} // Item[T] -> Item[S], Item[T]가 Item[S]의 서브형

    class Item3[+T](elem: T) {}
    class Other3(item : Item3[Any]) {
      def show = {
        this.item
      }
    }

    val a = new Other3(new Item3[String]("string"))
    println(a.show)
  }

  def typeAnnotationTest4: Unit = {
    // T -> S 와 같이 T가 S의 서브일때
    class Item4[-T](elem: T) {} // Item[S] -> Item[T], Item[S]가 Item[T]의 서브형

    class Other4(item: Item4[AnyRef]) {
      def show = {
        this.item
      }
    }

    // String -> AnyRef 이니까. Item4[AnyRef]는 Item4[String]의 서브로 정의된다.
    val a = new Other4(new Item4[String]("string"))

    // AnyRef -> Any 이니까 Item4[Any]는 Item4[AnyRef]의 서브형이 된다.
    val b = new Other4(new Item4[Any]("string"))
  }

  def main(args:Array[String]) = {
    //typeAnnotationTest1
    //typeAnnotationTest2
    typeAnnotationTest3
  }
}
