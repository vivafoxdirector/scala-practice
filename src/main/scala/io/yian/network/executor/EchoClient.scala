package io.yian.network.executor

import java.io._
import java.net.Socket

// ref: https://stackoverflow.com/questions/5055349/how-to-take-input-from-a-user-in-scala
object EchoClient {
    val HOST = "localhost"
    val PORT = 10000
    def main(args: Array[String]) : Unit = {
        try { // 서버 접속
            val sc = new Socket(HOST, PORT)
            val br = new BufferedReader(new InputStreamReader(sc.getInputStream))
            val pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream)))

            while (true) {
                try {
                    // write
                    val kin = scala.io.StdIn.readLine()
                    pw.println(kin)
                    pw.flush()

                    // read
                    val str = br.readLine
                    println(str)
                } catch {
                    case e: Exception =>
                        br.close()
                        pw.close()
                        sc.close()
                }
            }
        } catch {
            case ex: Exception => ex.printStackTrace()
        }
    }
}
