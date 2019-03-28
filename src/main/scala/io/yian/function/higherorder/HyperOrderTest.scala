package io.yian.function.hyperorder

import java.io.FileInputStream

object HyperOrderTest {

  def hyperOrderTest() = {
    val file = "E:\\00.WokrSpace\\git\\yian\\scala-practice\\src\\main\\scala\\io\\yian\\function\\curried\\ReadMe.md"
    val in = new FileInputStream(file)
    read(in, (l:String)=>{println(l)})
  }

  def read(in:java.io.InputStream, f:(String) => Unit) = {
    try {
      val reader = new java.io.BufferedReader(new java.io.InputStreamReader(in))
      var line: String = reader.readLine

      while (line != null) {
        f(line)
        line = reader.readLine
      }
    } finally {
      in.close
    }
  }

  def main(args:Array[String]) = {
    hyperOrderTest()
  }
}
