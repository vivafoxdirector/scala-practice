object Email {
  def apply(user:String, domain:String) = user + "@" + domain
  def unapply(str:String) : Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}

object UnapplyEmailTest extends App {
  Email("Yian", "ariari.com")
}

