package io.yian.patternmatch.effective

// ref : http://seratch.hatenablog.jp/entry/20110429/1304090707
object MatchTest01 {

  def matchTest01 = {
    var verbose = false

    val arr = Array[String]("-h", "-v")
    for (a <- arr) a match {
      case "-h" | "-help" => println("Usage: Main [-help | -verbose]")
      case "-v" | "-verbose" => verbose = true
      case x => println("Unknown option: '" + x + "'")
    }
    if (verbose)
      println("How are you today?")
  }

  // 타입 패턴매칭
  def matchTest02 = {
    abstract class Word(val value:String)
    class Noun(override val value:String) extends Word(value)
    class Verb(override val value:String) extends Word(value)
    def printWord(word: Any) = word match {
      case n: Noun => println("N:" + n.value)
      case w: Word => println(w.value) // Noun이외 Word의 서브형이 매칭
      case _ => println("---")
    }
    printWord(new Noun("Scala")) // N:Scala
    printWord(new Verb("learn")) // learn

  }

  // Extractor 패턴매칭
  // Extractor 는 대상 자료형의 인자를 받아서 Option을 리턴하는 unapply메소드를 가진 Singleton객체를 말한다.
  def matchTest03 = {
    object Csv {
      def unapply(csv:String): Option[Array[String]] = {
       csv match {
         case null | "" => None
         case value => Some(value.split(","))
       }
      }
    }
    "1,2,3" match {
      case Csv(values) => values foreach(println) // 1 2 3
      case _ =>
    }
  }

  // 케이스 클래스 패턴매칭
  // 케이스 클래스에서는 자동생성되는 컴패니언 오브젝트이므로 unapply메소드가 정의 되어 있기 때문에
  // Extractor로서 활용 할 수 있다.
  def matchTest04 = {
    abstract class Name
    case class FirstName(name: String) extends Name
    case class LastName(name: String) extends Name
    def printName(name:Name) : Unit = {
      name match {
        case FirstName(name) => println(name)
        case LastName(name) => println(name.toUpperCase)
        case _ =>
      }
    }
    printName(FirstName("Martin"))
    printName(LastName("Odersky"))
  }

  def main(args:Array[String]) = {
    matchTest01
    matchTest02
    matchTest03
    matchTest04
  }
}
