package io.yian.flatmapwith

// ref: https://qiita.com/mtoyoshi/items/c95cc88de2910945c39d
// ref: https://dev.classmethod.jp/server-side/scala-learn-flatmap/
// ref: http://ym.hatenadiary.jp/entry/2016/03/30/092337
// ref: http://tolarian-academy.net/option%E5%9E%8B%E3%81%AEmap%E3%81%A8flatmap%E3%81%8C%E3%82%88%E3%81%86%E3%82%84%E3%81%8F%E3%82%8F%E3%81%8B%E3%81%A3%E3%81%9F%EF%BC%88scala%E3%81%AE%E8%A9%B1%EF%BC%89/
// ref: https://alvinalexander.com/scala/collection-scala-flatmap-examples-map-flatten
// ref: http://www.brunton-spall.co.uk/post/2011/12/02/map-map-and-flatmap-in-scala/
object FlatMapTest {
  def main(args: Array[String]) = {
    var a = Seq(Seq(1,2,3), Seq(4), Seq(5,6)) flatMap{ x => x}
    var b = (Seq(Seq(1,2,3), Seq(), Seq(5,6))).flatten
    println(a)
    println(b)


  }
}
