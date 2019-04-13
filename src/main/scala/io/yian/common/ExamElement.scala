package io.yian.common

class ExamElement(var name: String, var desc:String, f: => Unit) {
    def play : Unit = {
        println(s"--------------- $name ---------------")
        println(s"::$desc")
        f
        println("--------------- E N D ---------------")
        println()
    }
}