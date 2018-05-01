package io.yian.traitwith

object Trait2 extends App {
    val man = new Man2("Yian", "Developer", "1")
    man say
}

trait Human {
    val name: String
}

trait Job {
    val job : String
}

trait Grade {
    val grade: String
}

class Man2(n: String, j:String, g:String) extends Human with Job with Grade {
    val name = n
    val job = j
    val grade = g

    def say(): Unit = {
        println(s"name: , job: $job, grade: $grade")
    }
}