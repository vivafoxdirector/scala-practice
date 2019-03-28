package io.yian.courses.edx

// ref: https://courses.edx.org/courses/course-v1:EPFLx+scala-reactiveX+1T2019/courseware/b97676e54fa34c038d1429ab8c0aee66/1441e1f1ad22482d8933fefa12f8862a/?child=first
abstract class JSON

case class JSeq(elems: List[JSON]) extends JSON

case class JObj(bindings: Map[String, JSON]) extends JSON

case class JNum(num: Double) extends JSON

case class JStr(str: String) extends JSON

case class JBool(b: Boolean) extends JSON

case object JNull extends JSON

object LectureOne {
  val data = JObj(Map(
    "firstName" -> JStr("John"),
    "lastName" -> JStr("Smith"),
    "address" -> JObj(Map(
      "streetAddress" -> JStr("21 2nd Street"),
      "state" -> JStr("NY"),
      "postalCode" -> JNum(10021)
    )),
    "phoneNumbers" -> JSeq(List(
      JObj(Map(
        "type" -> JStr("home"), "number" -> JStr("212 555-1234")
      )),
      JObj(Map(
        "type" -> JStr("fax"), "number" -> JStr("646 555-4567")
      ))
    ))
  ))

  def show(json: JSON): String = json match {
    //case JSeq(elems) => "[" + (elems map show mkString ", ") + "]"
    case JSeq(elems) => "[" + elems.map(e => show(e).mkString(", ")) + "]"
    case JObj(bindings) =>
      //val assocs = bindings map {
      val assocs = bindings map {
        case (key, value) => "\"" + key + "\": " + show(value)
      }
      "{" + assocs.mkString(", ") + "}"
    case JNum(num) => num.toString
    case JStr(str) => '\"' + str + '\"'
    case JBool(b) => b.toString
    case JNull => "null"
  }

  def main(args: Array[String]): Unit = {
    println(show(data))
  }

}
