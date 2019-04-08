package io.yian.network.akka

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.io.{IO, Tcp}

// ref : https://qiita.com/castaneai/items/d2746523d51cd73d6aaf
class Client(remoteAddress : InetSocketAddress) extends Actor with ActorLogging {

  import Tcp._
  import context.system

  IO(Tcp) ! Connect(remoteAddress)

  def receive = {
    case Connected(remote, local) =>
      log.info("{}에 접속함", remote)

    case CommandFailed(_) =>
      log.error("실패")
      context stop self
  }
}

object EchoClient {
  def main(args:Array[String]) = {
    val system = ActorSystem("MySystem")
    val remoteAddress = new InetSocketAddress("localhost", 12345)
    system.actorOf(Props(classOf[Client], remoteAddress))
    println("")
  }
}
