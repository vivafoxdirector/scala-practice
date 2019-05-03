package io.yian.network.thread

import java.io._
import java.net.Socket

class SocketAcceptRunnable(val sc: Socket) extends Runnable {
    override def run(): Unit = {
        //val br = new BufferedReader(new InputStreamReader(sc.getInputStream))
        val br = new BufferedInputStream(sc.getInputStream)
        val pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream)))
        try {
            while (true) {
                //val str = br.readLine()
                var data = new Array[Byte](10240)
                val length = br.read(data)  //val length = br.read(data, 0, 10240)
                if (length > 0) {
                    println("data: " + data)
                    println("leng: " + length)

                    var msg = data.slice(0, length)
                    println("msg: " + (msg.map(_.toChar)).mkString)
                    println("full: " + (data.map(_.toChar)).mkString)
                }
                //pw.println(str)
                //pw.flush()
            }
        } catch {
            case t: Throwable => println(t.getMessage)
        } finally {
            br.close()
            pw.close()
            sc.close()
        }
    }
}
