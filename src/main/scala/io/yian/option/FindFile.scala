import java.io.File

object FindFile extends App {
  def findFile(filename:String) : Option [File] = {
    val file = new File(filename)
    if (file.exists) Some(file)
    else None
  }
  println(findFile("./test.csv").getOrElse(""))
/*
  findFile("./test.csv") match {
    case Some(x) => println(x)
    case None => println("None")
  }*/
}
