package io.yian.network.executor

import java.io._
import java.net.Socket

class SocketAcceptRunnable(val sc: Socket) extends Runnable {
    override def run(): Unit = {
        val br = new BufferedReader(new InputStreamReader(sc.getInputStream))
        val pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream)))
        try {
            while (true) {
                val str = br.readLine()
                pw.println(str)
                pw.flush()
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