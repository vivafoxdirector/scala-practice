package io.yian.flatmapwith

import io.yian.common.ExamHolder

import scala.util.Random

// ref: https://qiita.com/mtoyoshi/items/c95cc88de2910945c39d
// ref: https://dev.classmethod.jp/server-side/scala-learn-flatmap/
// ref: http://ym.hatenadiary.jp/entry/2016/03/30/092337
// ref: http://tolarian-academy.net/option%E5%9E%8B%E3%81%AEmap%E3%81%A8flatmap%E3%81%8C%E3%82%88%E3%81%86%E3%82%84%E3%81%8F%E3%82%8F%E3%81%8B%E3%81%A3%E3%81%9F%EF%BC%88scala%E3%81%AE%E8%A9%B1%EF%BC%89/
// ref: https://alvinalexander.com/scala/collection-scala-flatmap-examples-map-flatten
// ref: http://www.brunton-spall.co.uk/post/2011/12/02/map-map-and-flatmap-in-scala/
object FlatMapTest {
  // 제너레이터가 한개인 경우
  def flatMapTest001: Unit = {
    // for 루프틑 foreach로 변환되서 실행된다.
    for (i <- 1 to 5) println(i)

    // 위와 같다.
    (1 to 5).foreach(i => println(i))

    // 위의 for루프를 아래와 같이 사용할 수 있다.
    // 다른 방식은 내포 표기법이다.
    // for yield는 map 과 같다
    val r1 = for (i <- 1 to 5) yield i + 1
    println(r1)

    val r2 = (1 to 5).map(i => i + 1)
    println(r2)
  }

  // 제너레이터가 복수개인 경우
  def flatMapTest002:Unit = {
    // 아래의 예제의 결과값은 모두 같다.

    // 제너레이터가 복수개사용 for 루프
    for (a <- 1 to 5; b <- Seq("A","B")) yield println(s"$a-$b")
    for {
      a <- 1 to 5
      b <- Seq("A", "B")
    } yield {
      println(s"$a-$b")
    }

    // 위의 for루프를 아래와 같이 사용할 수 있다.
    // 제너레이터가 복수개 사용 flatMap
    val e1 = 1 to 5
    val e2 = Seq("A","B")

    // p1 <- e1 를 전개
    e1.flatMap(p1 => for {p2 <- e2} yield println(s"$p1-$p2"))

    // p1 <- e1 / p2 -> e2 둘다 전개
    e1.flatMap(p1 => e2.map(p2 => s"$p1-$p2"))
  }

  def flatMapTest01 = {
    var a = Seq(Seq(1,2,3), Seq(4), Seq(5,6)) flatMap{ x => x}
    var b = (Seq(Seq(1,2,3), Seq(), Seq(5,6))).flatten
    println(a)
    println(b)
  }
  //============================================
  // ref: https://dev.classmethod.jp/server-side/scala-learn-flatmap/
  def findBestFriehd(userId: String) : Option[String] = {
    val friends:List[String] = findFriend(userId)
    friends.headOption
  }

  def findFriend(id:String):List[String] = {
    //https://qiita.com/takilog/items/054bdad9afd816ee0a45
    val r = new Random
    val map = Map("Yian"-> List("Kain","Sein","Nine","Tian"),
      "Kain"-> List("Yian","Sein","Nine","Tian"),
      "Sein"-> List("Yian","Kain","Nine","Tian"),
      "Nine"-> List("Yian","Sein","Kain","Tian"),
      "Tian"-> List("Yian","Sein","Nine","Kain"))
    val list = map(id)
    r.shuffle(list)
    // r.shuffle(1 to 10) // 수치값 1~10까지 랜덤
  }

  def flatMapTest02_AsIs1 = {
    val maybeBestFriend = findBestFriehd(userId = "Yian")
    val result = maybeBestFriend match {
      case None => "친구 따윈 필요 없어"
      case Some(bestFriend) => s"${bestFriend}는 나의 가장 친한 친구"
    }
    println(result)
  }

  def flatMapTest02_AsIs2 = {
    if(!hasMutualBestFriendAsIs("Yian")) {
      println("짝사랑!")
    }
  }

  def hasMutualBestFriendAsIs(userId: String) : Boolean = {
    val maybeBestFriend: Option[String] = findBestFriehd(userId)
    maybeBestFriend match {
      case None => false
      case Some(bestFriend) => println(s"${userId}의 가장 친한 친구는 ${bestFriend}")
        val mayBeBestFriendOfBestFriend: Option[String] = findBestFriehd(bestFriend)
        mayBeBestFriendOfBestFriend match {
          case None => false
          case Some(bestFriendOfBestFriend) => {
            println(s"${bestFriend}의 가장 친한 친구는 ${bestFriendOfBestFriend}")
            userId == bestFriendOfBestFriend
          }
        }
    }
  }

  def flatMapTest02_ToBe = {
    val maybeUserId:Option[String] = Some("Yian")

    val result = maybeUserId.flatMap { userId =>
      findBestFriehd(userId)
    }
    println(result)
  }

  def flatMapTest02_ToBe1 = {
    if (!hasMutualBestFriendTobe1("Yian")) {
      println("짝사랑!")
    }
  }
  def flatMapTest02_ToBe2 = {
    if (!hasMutualbestFriendTobe2("Yian")) {
      println("짝사랑!")
    }
  }
  def flatMapTest02_ToBe3 = {
    if (!hasMutualbestFriendTobe3("Yian")) {
      println("짝사랑!")
    }
  }
  //--------------------- 줄여보자 ------------------------
  def hasMutualBestFriendTobe1(userId:String): Boolean = {
    val maybeBestFriend: Option[String] = findBestFriehd(userId)
    val eachBestFriend: Option[Boolean] = maybeBestFriend.flatMap {
      bestFriend =>
        val maybeBestFriendOfBestFriend:Option[String] = findBestFriehd(bestFriend)
        maybeBestFriendOfBestFriend.map { bestFriendOfBestFriend => userId == bestFriendOfBestFriend}
    }
    eachBestFriend getOrElse  false
  }
  //------------------ 좀더 줄여보자 -----------------------
  def hasMutualbestFriendTobe2(userId:String) : Boolean = {
    findBestFriehd(userId).flatMap { bestFriend =>
      findBestFriehd(bestFriend).map { bestFriendOfBestFriend => userId == bestFriendOfBestFriend}
    }.getOrElse(false)
  }
  //------------------ 좀더 줄여보자 -----------------------
  def hasMutualbestFriendTobe3(userId:String) : Boolean = {
    findBestFriehd(userId).flatMap(findBestFriehd).contains(userId)
  }


  def main(args: Array[String]) = {
    val a = new ExamHolder("Function")
    a.addFunc("flatMapTest001", "제너레이터 1개", flatMapTest001)
    a.addFunc("flatMapTest002", "제너레이터 복수개", flatMapTest002)
    a.addFunc("flatMapTest02_AsIs1", "flatMapTest02_AsIs1", flatMapTest02_AsIs1)
    a.addFunc("flatMapTest02_AsIs2", "flatMapTest02_AsIs2", flatMapTest02_AsIs2)
    a.addFunc("flatMapTest02_ToBe", "flatMapTest02_ToBe", flatMapTest02_ToBe)
    a.addFunc("flatMapTest02_ToBe1", "flatMapTest02_ToBe1", flatMapTest02_ToBe1)
    a.addFunc("flatMapTest02_ToBe2", "flatMapTest02_ToBe2", flatMapTest02_ToBe2)
    a.addFunc("flatMapTest02_ToBe3", "flatMapTest02_ToBe3", flatMapTest02_ToBe3)
    a.selectFunc
  }
}
