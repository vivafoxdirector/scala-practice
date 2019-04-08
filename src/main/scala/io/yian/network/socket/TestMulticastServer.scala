package io.yian.network.socket

import java.io.OutputStreamWriter
import java.net._

object TestMulticastServer {
  class MulticastServer(val address: String, val port:Int) {
    val socket = new MulticastSocket(port)
    val multicastAddressGroup = InetAddress.getByName(address)
    socket.joinGroup(multicastAddressGroup)
    val serverSocket = new ServerSocket()
    serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 0))

    def execute: Unit = {
      while(true) {
        val buffer = Array.ofDim[Byte](1024)
        val data = new DatagramPacket(buffer, buffer.length)
        try {
          socket.receive(data)
          checkMessage(new String(data getData).trim)
        } catch {
          case e:SocketTimeoutException => ()
        }
      }
    }
    private def checkMessage(msg: String) : Unit = {
      if (msg.startsWith("QUERY")) sendReply(msg)
    }

    private def sendReply(msg: String):Unit = {
      log("send reply for message: " + msg)
      val destination = msg.replace("QUERY:","").split(":")
      var optSocket:Option[Socket] = None
      try {
        optSocket = Some(new Socket(InetAddress.getByName(destination(0)), destination(1).toInt))
        val writer = new OutputStreamWriter(optSocket.get.getOutputStream)
        writer.write("{'reply' : {'address':'" + InetAddress.getLocalHost.getHostName + "', 'port':'" + serverSocket.getLocalPort+"'}}\r\n")
        writer.flush()
      } finally {
        optSocket match {
          case Some(socket) => socket.close()
          case _ => ()
        }
      }
    }

    def log(msg: String) : Unit = println("Server: " + msg)
  }

  def main(args:Array[String]):Unit = {
    if (args.length < 1) {
      println("No address specified")
      System.exit(-1)
    }
    val address = args(0)
    new MulticastServer(address, 1900).execute
  }
}
