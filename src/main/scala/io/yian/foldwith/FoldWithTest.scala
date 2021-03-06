package io.yian.foldwith

import io.yian.common.ExamHolder

// ref: https://dev.classmethod.jp/server-side/scala-differece-between-fold-and-foldleft/
// ref: https://dev.classmethod.jp/server-side/scala-foldright-foldleft/
// ref: http://allaboutscala.com/tutorials/chapter-8-beginner-tutorial-using-scala-collection-functions/scala-foldleft-example/
// ref: http://dwango.github.io/scala_text_previews/trait-tut/collection.html

// Fold
// ref: https://qiita.com/Kazuhiro23/items/3a1a21544d71b5c76e78

// fold/foldLeft/foldRight
// ref: https://code.i-harness.com/ja-jp/q/5f6d9a
object FoldWithTest {

  //=======================================================
  // fold and foldLeft and foldRight
  // ref: https://code.i-harness.com/ja-jp/q/5f6d9a
  def foldTest000 : Unit = {
    // fold
    val l = List(5,6,3,2,7,4,2,1)
    l.fold(0) {
      (x, y) => println("fold left: " + x + ", right: " + y)
        x + y
    }

    // foldLeft
    l.foldLeft(0) {
      (x, y) => println("foldLeft left: " + x + ", right: " + y)
        x + y
    }

    // foldRight
    l.foldRight(0) {
      (x, y) => println("foldRight left: " + x + ", right: " + y)
        x + y
    }
  }

  // ref: https://qiita.com/Kazuhiro23/items/3a1a21544d71b5c76e78
  def foldTest001 = {
    // have money
    val maybeMoney : Option[Int] = Some(1000)
    val message = maybeMoney.fold("i dont hav money!") { money => s"${money}dollors i have"}
    println(message)
  }
  def foldTest002 = {
    // have not money
    val maybeMoney : Option[Int] = None
    val message = maybeMoney.fold("i dont hav money!") { money => s"${money}dollors i have"}
    println(message)
  }

  //=======================================================
  // 문자열의 특정 키워드를 바꾸는 처리를 foreach사용해서 해보기
  // ref: https://qiita.com/t-hiroyoshi/items/ed26cd26eb5a2d3d379b
  def foldLeftTest001 = {
    val keywords = Map("foo" -> "bar", "baz" ->"qux")
    val text = "foo is baz."
    val changedText = keywords.foreach {map => text.replace(map._1, map._2)}
    println(changedText)  // 안된다.
  }
  // foreach로 안되니 foldLeft로 해본다.
  def foldLeftTest002 = {
    val keywords = Map("foo" -> "bar", "baz" ->"qux")
    val text = "foo is baz."
    val changedText = keywords.foldLeft(text){(tex, map) => tex.replace(map._1, map._2)}
    println(changedText)
  }
  //=======================================================

  def main(args:Array[String]) = {
    val a = new ExamHolder("Fold")
    a.addFunc("foldTest000", "compare fold and foldLeft and foldRight", foldTest000)
    a.addFunc("foldTest001", "foldTest", foldTest002)
    a.addFunc("foldTest002", "foldTest", foldTest002)
    a.addFunc("foldLeftTest001", "using foreach", foldLeftTest001)
    a.addFunc("foldLeftTest002", "using foldLeft", foldLeftTest002)
    a.selectFunc
  }
}