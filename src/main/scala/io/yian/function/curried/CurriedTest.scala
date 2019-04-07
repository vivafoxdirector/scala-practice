// ref: https://gist.github.com/nenono/2523c406735b11ee9b46

// Thread
// ref: https://twitter.github.io/scala_school/concurrency.html
object CurriedTest {

  def add(x:Int, y:Int, z:Int) = x + y + z
  def sum(a:Int)(b:Int)(c:Int) = a + b + c
  def sumCurry(a:Int) = (b:Int) => (c:Int) => a + b +c

  def curryTest1 = {
    println(sum(1)(2)(3))
    val cf = sumCurry(1)
    println(cf)
    println(cf(2)(3))
  }

  def curryTest2 = {
    // def로 정의한 메소드는 커리화할 수 없기 때문에 "스페이스 + _"을 이용하여 함수 오브젝트를 취득한다.
    val addFunc = add _ // add  _ 의 의미는 add(_,_,_)와 같다.

    val a = addFunc.curried  // curried function으로 바꾼다
    val a1 = a(2)   // add의 첫번째 인자
    val a2 = a1(1)  // add의 두번째 인자
    val a3 = a2(1)  // add의 세번째 인자

    println(s"result: ${a1}, ${a2}, ${a3}")
    // StringContext클래스의 메소드호출로 변환됨.
    // 이경우 new StringContext("result: ", ", ", ", ").s(a1, a2, a3) 가 됨

    val a4 = (add _ curried)(1)(1)(1)
    println(s"result: ${a4}")
  }

  // general function
  //def cubicVolumn(x:Int, y:Int, z:Int):Int = x + y + z

  def cubicVolumn(x:Int)(y:Int)(z:Int):Int = x * y * z

  def cubicVolumnFull(x:Int):(Int) => ((Int) => Int) = {
    def cubicVolumnFull_X(y:Int):(Int) => Int = {
      def cubicVolumnFull_XY(z:Int):Int = {
        return x * y * z
      }
      return cubicVolumnFull_XY _
    }
    return cubicVolumnFull_X _
  }

  // Change curried function from general function 'cubicVolumnFunction'
  def cubicVolumnFunction(x:Int, y:Int, z:Int) : Int = x * y * z

  //======================================================================
  // Myself
  def curriedTest05 = {
    def addfun(a:Int) = {
      println(a + a)
    }

    def curriedFunc(f:(Int) => Unit)(m:Int) = {
      f(m)
    }

    val add1 = curriedFunc((l:Int) => {println(l+l)})_
    add1(10)  // => 20
    add1(20)  // => 40

    val add2 = curriedFunc(addfun)_
    add2(10)  // => 20
    add2(20)  // => 40
  }
  //======================================================================
  def curriedTest06 = {
    // Curried Thread
    def curriedFunc(f:(String)=>Unit)(name:String) = {
      f(name)
    }

    def executor(s:String) : Unit = {
      val hello = new Thread(new Runnable {
        def run() {
          println(s)
          Thread.sleep(1000)
        }
      })
      hello.start
    }

    val thread = curriedFunc(executor)_

    thread("hello")
    thread("world!")
  }
  //======================================================================
  def curriedTest07 = {
    def curriedFunc(name:String)(codeBlock: => Unit) = {
      val thread = new Thread(name) {
        override def run(): Unit = {
          codeBlock
        }
      }
      thread.setDaemon(true)
      thread.start()
      thread
    }

    curriedFunc("MyThread") {
      println("thread...")
      println("thread...")
      println("thread...")
      println("thread...")
      println("thread...")
    }
  }
  //======================================================================

  def main(args: Array[String]) {
    curryTest1
    curryTest2

    val cubicVolumn_X = cubicVolumn(2)_
    println(cubicVolumn_X(3)(4))

    val cubicVolumn_XY = cubicVolumn_X(3)
    println(cubicVolumn_XY(4))

    val cubicVolumnResult = cubicVolumn_XY(4)
    println(cubicVolumnResult)

    val r = cubicVolumn(2)_
    println(r(3)(4))

    val c = cubicVolumnFull(2)
    val cx = c(3)
    val cxy = cx(4)
    println(cxy)

    //println(cubicVolumnFull(2)(3)(4))

    val gf = cubicVolumnFunction _
    val cubicVolumnCurried = gf.curried
    val cubicVolumnCurried_X = cubicVolumnCurried(2)
    val cubicVolumnCurried_XY = cubicVolumnCurried_X(3)

    println(cubicVolumnCurried_XY(4))

    println("---------------------------------")
    curriedTest05
    curriedTest06
    curriedTest07
  }
}
