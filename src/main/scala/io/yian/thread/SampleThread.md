class SampleThread(list:List[Int]) extends Thread {
  override def run() = {
    println(list.sum)
  }
}

val s = new SampleThread(List.range(1, 10000))
s.start
s.join
