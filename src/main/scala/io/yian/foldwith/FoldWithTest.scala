package io.yian.foldwith

// ref: https://qiita.com/t-hiroyoshi/items/ed26cd26eb5a2d3d379b
// ref: https://dev.classmethod.jp/server-side/scala-foldright-foldleft/
object FoldWithTest {

  //=======================================================
  // 문자열의 특정 키워드를 바꾸는 처리를 foreach사용해서 해보기
  def foldLeftTest01 = {
    val keywords = Map("foo" -> "bar", "baz" ->"qux")
    val text = "foo is baz."
    val changedText = keywords.foreach {map => text.replace(map._1, map._2)}
    println(changedText)  // 안된다.
  }
  // foreach로 안되니 foldLeft로 해본다.
  def foldLeftTest02 = {
    val keywords = Map("foo" -> "bar", "baz" ->"qux")
    val text = "foo is baz."
    val changedText = keywords.foldLeft(text){(tex, map) => tex.replace(map._1, map._2)}
    println(changedText)  // 안된다.
  }
  //=======================================================
  // ref: https://qiita.com/Kazuhiro23/items/3a1a21544d71b5c76e78
  def foldTest01 = {
    // have money
    val maybeMoney : Option[Int] = Some(1000)
    val message = maybeMoney.fold("i dont hav money!") { money => s"${money}dollors i have"}
    println(message)
  }
  def foldTest02 = {
    // have not money
    val maybeMoney : Option[Int] = None
    val message = maybeMoney.fold("i dont hav money!") { money => s"${money}dollors i have"}
    println(message)
  }
  //=======================================================
  def main(args:Array[String]) = {
    foldTest01
    foldTest02
    foldLeftTest01
    foldLeftTest02
  }
}
