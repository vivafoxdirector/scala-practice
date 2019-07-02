package io.yian.option

import io.yian.common.ExamHolder

/**
  * ref: https://blog.shibayu36.org/entry/2015/08/31/103000
  */
object OptionEitherTest {

    // Some 과 None이용하여 패턴매칭으로 예외 처리
    def OptionEitherTest001() : Unit = {

        val map = Map("a" -> 1, "b" -> 2)

        // Map의 get은 Option 반환
        // 값이 있으므로 a 출력
        map.get("a") match {
            case Some(n) => println(n)
            case None => println("Nothing")
        }

        // Map의 get은 Option
        // 값이 없으므로 예외는 발생 안하고 Nothing 출력
        map.get("c") match {
            case Some(n) => println(n)
            case None => println("Nothing")
        }
    }

    // getOrElse 사용
    def OptionEitherTest002() : Unit = {

        val map = Map("a" -> 1, "b" -> 2)
        println(map.get("a").getOrElse(3))
        println(map.get("c").getOrElse(3))
    }

    // flatMap 과 map을 이용
    def OptionEitherTest003() : Unit = {
        val map = Map("a" -> 1, "b" -> 2)

        map.get("a").flatMap(a =>
            map.get("b").map(b => {
                println(a + b)
                a+b
            })
        )

        map.get("a").flatMap(a =>
            map.get("c").map(c => {
                println(a + c)
                a + c
            })
        )

        map.get("c").flatMap(c =>
            map.get("b").map(b => {
                println(b + c)
                b + c
            })
        )
    }

    // flatMap 과 map 대신에 for를 이용
    def OptionEitherTest004(): Unit = {
        val map = Map("a" -> 1, "b"->2)

        for {
            a <- map.get("a")
            b <- map.get("b")

        } {
            println(a+b)
        }

        for {
            a <- map.get("a")
            c <- map.get("c")

        } {
            println(a + c)
        }

        for {
            c <- map.get("c")
            b <- map.get("b")
        } {
            println(c + b)
        }
    }

    def main(args: Array[String]): Unit = {
        val a = new ExamHolder("OptionEitherTest")
        a.addFunc("OptionEitherTest001", "Pattern Matching", OptionEitherTest001)
        a.addFunc("OptionEitherTest002", "getOrElse", OptionEitherTest002)
        a.addFunc("OptionEitherTest003", "flatMap and map", OptionEitherTest003)
        a.addFunc("OptionEitherTest004", "for", OptionEitherTest004)

        a.selectFunc
    }
}