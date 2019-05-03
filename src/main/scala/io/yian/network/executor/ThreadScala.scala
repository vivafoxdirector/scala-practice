package io.yian.network.executor

object ThreadScala {

    def startDaemon(name: String)(codeBlock: => Unit): Thread = {
        val thread = new Thread(name) {
            override def run(): Unit = {
                try {
                    codeBlock
                } catch {
                    case t: Throwable => t.printStackTrace
                }
            }
        }
        thread.setDaemon(true)
        thread.start
        thread
    }
}
