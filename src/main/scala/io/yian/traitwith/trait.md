# Trait

## 트레이트 란

트레이트는 Java의 인터페이스와 유사한 점이 있다. 우선 Java의 인터페이스에 대하여 설명하면서 트레이트를 간략히 설명한다. 

자바는 2개 이상의 클래스를 상속 받을 수 없기 때문에 여러개의 인터페이스를 상속하여 다중 상속과 같은 효과를 낼 수 있다. 하지만 인터페이스는 메소드 선언만 할 수 있으므로 인터페이스를 상속받은 모든 클래스는 선언된 메소드를 정의해야 한다. 이처럼 인터페이스를 상속받은 클래스들의 메소드 정의는 대부분 비슷한 코드를 가지는 경우가 있다. 인터페이스에 직접 메소드 정의를 할 수 없기 때문에 클래스에서 메소드 정의를 할 수 밖에 없는게 현실이다.

Scala도 Java와 마찬가지로 다중 상속을 할 수 없지만, 인터페이스 대신에 트레이트를 사용 할 수 있다. 트레이트는 Java의 인터페이스와 마찬가지로 여러개의 트레이트를 mix-in할 수 있다.

Scala의 트레이트와 Java의 interface의 차이점은 [트레이트는 메소드 선언뿐만 아니라 정의도 할 수 있다.] 라는 점이다. 이를 위해 공통적으로 사용할 수 있는 메소드의
정의를 트레이트에 기술하여 이를 상속받은 클래스는 메소드의 정의없이 사용 할 수 있다.

## 트레이트 사용방법
트레이트는 [trait]키워드를 사용한다. 정의 방법은 클래스 정의와 비슷하다. 클래스와 마찬가지로 접근 제한 식별자도 지정할 수 있고, 다른 클래스를 상속받거나, 다른 trait를 mix-in 할 수 있다.
```scala
<접근제한식별자> trait 트레이트명 {
    // 멤버 변수 정의
    // 메소드 정의
}
```
실제로 예제를 작성해본다. 먼저 프로그램을 표현하는 트레이트를 정의한다. 이 트레이트는 coding메소드를 가지고 있다. 그리고 Programmer트레이트를 믹스인하는 Person클래스도 정의한다.
```scala
trait Programmer {
  def config = println("welcome trait!!")
}
class Person(val name:String) extends Programmer

val p = new Person("yian")
p.config

```
### 트레이트로 Programmer 믹스인
클래스를 명시적으로 상속받는 않는 경우 [extends]를 사용해서 트레이트를 믹스인한다. 만일 추가로 다른 클래스나 오브젝트를 상속받을 경우는 [with] 키워드를 사용하여 트레이트를 믹스인 한다.

아래는 ChildClass는 ParentClass를 계승하고, TraitA와 TraitB를 믹스인 한것이다.
```scala
class ChildClass extends ParentClass with TraitA with TraitB
```

### 믹스인한 Person 사용
Person클래스를 인스턴스화하여 사용해 본다. Programmer트레이트를 믹스인하고 있어서 coding메소드를 사용할 수 있다.
```scala
val p = new Person("Yian")
p.coding

```

### 인스턴스화시 믹스인
또다른 믹스인 정의 방법 인스턴스를 만들때 믹스인을 정의할 수 있다. 아래와 같이 디자이너 트레이트를 만들고, 인스턴스화 할 때 믹스인하는 것이다.
```scala
trait Programmer {
  def config = println("welcome trait!!")
}
class Person(val name:String) extends Programmer

trait Design {
    def design = println("designing...")
}

val p = new Person("Yian") with Design
p.coding
p.design
```
위와 같이 new로 인스턴스화하고, 그대로 [with]키워드를 이용하여 트레이트를 믹스인한다.

## 믹스인 주의점
### 트레이트로 생성자 처리
트레이트는 메소드뿐만 아니라 멤버 변수도 가진다. 그리고 코드를 작성하면 생성자 처리도 할 수 있다. 그러나 기본 생성자에 전달하기 위한 파라메타를 가져오면
보조 생성자를 정의 할 수 없다는 것에 주의 해야 한다.

```scala
trait ProjectManager {
  val budget:Int = 10000000
  println("budget = " + budget)
  def manage = println("프로젝트관리")
}
```
Person인스턴스 생성시에 ProjectManager트레이트를 믹스인 한다.
```scala
repl> val p = new Person("Yian") with ProjectManager
budget = 10000000
...
```
REPL로 위 예제를 실행하면 인스턴스화 하는 동시에 트레이트의 생성자가 실행되는 것을 확인 할 수 있다.

### 여러개의 트레이트 사용시 생성자 실행 순서
복수의 트레이트를 사용하는 경우 생성자가 실행되는 순서는 어떻게 될까? 몇가지 예제를 들어 알아본다
```scala
class Parent {
  println("Parent")
}
class Child extends Parent {
  println("Child")
}
trait A {
  println("trait A")
}
trait B {
  println("trait B")
}
trait C {
  println("trait C")
}

val c = new Child with A with B with C 

```
슈퍼 클래스 생성자 다음, 자기 자신의 생성자가 호출되고, 이후 믹스인한 가장 좌측에서 우측으로 호출이 되는 것을 확인 할 수 잇다.

### 별개의 트레이트의 시그니처(메소드) 이름이 같은 경우
의도하지 않게 여러 트레이트의 메소드명이 같은 경우가 있을 것이다. 이경우 어떻게 동작을 하는지 아래와 같은 예제를 들어 본다.
```scala
trait Programmer {
    def write = println("coding...")
}
trait Writer {
    def write = println("writing...")
}
``` 
위 2개의 트레이트는 같은 메소드 명으로 되어 있다. 이들 2개의 트레이트를 믹스 인 하게 되면 에러가 발생 된다.
```scala
repl> class Person extends Programmer with Writer
error...
```
같은 시그니쳐의 메소드를 가지는 트레이트를 믹스인한 클래스에서는 그 메소드를 반드시 [override] 키워드를 사용해야 한다.
```scala
class Person extends Programmer with Writer {
    override def write = println("writing document.....")
}

repl> val p = new Person
repl> p.write
```

## 트레이트에서 [super] 사용

 
 
### REF
https://qiita.com/chara06ken/items/8e6b26d5857d52bbece5
https://qiita.com/f81@github/items/5b96af593812286eec49
https://gist.github.com/gakuzzzz/10081860
https://www.atmarkit.co.jp/ait/articles/1206/20/news137.html
http://dwango.github.io/scala_text/trait.html