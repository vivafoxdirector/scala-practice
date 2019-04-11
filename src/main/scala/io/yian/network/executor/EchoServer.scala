package io.yian.network.executor

import java.net.ServerSocket
import java.util.concurrent.{Executors}

object EchoServer {
    val PORT = 10000

    def main(args: Array[String]): Unit = {
        val ss = new ServerSocket(PORT)
        val es = Executors.newSingleThreadExecutor()
        while (true) {
            try {
                val sc = ss.accept()
                es.execute(new SocketAcceptRunnable(sc))
            } catch {
                case e : Exception => println(e.getMessage())
            }
        }
    }
}
