package io.yian.network.akka

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.io.{IO, Tcp}

// ref : https://qiita.com/castaneai/items/0b24e2bce85adb2044a9
class Handler extends Actor {

  import Tcp._

  def receive = {
    case Received(data) => sender() ! Write(data)
    case PeerClosed => context stop self
  }
}

class Server(bindAddress: InetSocketAddress) extends Actor with ActorLogging {
  import Tcp._
  import context.system
  IO(Tcp) ! Bind(self, bindAddress)

  def receive = {
    case Bound(localAddress) =>
      log.info("bound on {}...", localAddress)

    case Connected(remote, local) =>
      log.info("accepted peer: {}", remote)
      val handler = context.actorOf(Props[Handler])
      sender() ! Register(handler)

    case CommandFailed(_: Bind) =>
      log.error("bind failed")
      context stop self
  }
}

object EchoServer {
  def main(args: Array[String]) = {
    val system = ActorSystem("MySystem")
    val bindAddress = new InetSocketAddress("localhost", 12345)
    system.actorOf(Props(classOf[Server], bindAddress))
    println("")
  }
}