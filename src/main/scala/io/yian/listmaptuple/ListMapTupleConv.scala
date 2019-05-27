package io.yian.listmaptuple

/**
  * ref: http://chopl.in/post/2013/02/22/scala-case-class-to-list-map-tuple/
  */
object ListMapTupleConv {

    def ListMapTupleTest001() : Unit = {
        case class Foo(i : Int, s: String)

        val f = Foo(1,"Yian")
        val l = f.productIterator.toList

        println(l)
    }

    def main(args:Array[String]) = {
        ListMapTupleTest001()
    }

}
