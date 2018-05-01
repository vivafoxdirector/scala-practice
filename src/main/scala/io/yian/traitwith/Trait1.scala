package io.yian.traitwith

object Trait1 extends App {
    val man = new Man
    man.sayName()
}

trait Human1 {
    val name = "Yian"

    def sayName(): Unit = {
        println(s"My name is $name")
    }
}

class Man extends Human1
