package io.yian.advance.recursive

object RecursiveTest {
    def sum(head:Int, list:List[Int]) : Int = {
        print(head + " ")
        list match {
            case Nil => head // Nil 이면 head를 return한다.
            case h::t => sum(head + h, t)
        }
    }

    def main(args:Array[String]) : Unit = {
        val list = List(1,2,3,4,5,6,7)
        val result = sum(list.head, list.tail)
        println("result: " + result)
    }
}
