package io.yian.network.echo

import java.net.{ServerSocket}

object ServerMain {
  val PORT = 10000
  def main(args:Array[String]) = {
    val ss = new ServerSocket(PORT)
    while(true) {
      try {
        val sc = ss.accept()
        println("WelCome!!")

        val cc = new SocketAcceptRunnable(sc)
        cc.run()
      } catch {
        case e : Exception => println(e.getMessage)
      }
    }
  }
}
