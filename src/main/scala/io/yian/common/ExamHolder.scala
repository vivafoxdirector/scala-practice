package io.yian.common

class ExamHolder(name: String) {
    // List Initialize
    //var factory = List[ExamElement]()
    //val factory = new java.util.ArrayList[ExamElement]
    var factory = List.empty[ExamElement]

    // Add func
//    def addFunc(name:String, f: => Unit) {
//        addFunc(name, "Nothing!", f)
//    }

    def addFunc(name:String, f: => Any) {
        addFunc(name, "nop..!", f)
    }

    def addFunc(name:String, desc: String, f: => Any) {
        factory ::= new ExamElement(name, desc, f)
    }

    // print All Func Name
    def printAllFuncName : Unit = {
        var index = 0
        factory.foreach(m => {
            index+=1
            println(index + ". "+  m.name)
        })
    }

    // select func
    def selectFunc : Unit = {
        while(true) {
            // print exam name
            printAllFuncName

            // input exam name
            print("num > ")
            var k = scala.io.StdIn.readLine()

            // is space ??
            if (k == "") return

            // check digit
            if (!(k forall{_.isDigit})) return

            // cast digit from string
            var ok = k.toInt

            // check element existed
            if (!(factory.size < ok || ok < 1 )) factory(ok-1).play
            else println(".....nothing!.....")
        }
    }

    // select func with funcname
    def searchFuncPlay(name:String) = {
        factory.foreach(m =>
            m match {
                case e: ExamElement => e.play
                case _ => Nil
            }
        )
    }
}