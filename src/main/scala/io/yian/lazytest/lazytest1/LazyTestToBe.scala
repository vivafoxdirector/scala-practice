package io.yian.lazytest.lazytest1

// ref: https://kujira16.hateblo.jp/entry/20111119/1321708622
object LazyTestToBe extends App {
  def tarai(nx: => Int, ny: => Int, nz: => Int): Int = {
    lazy val x = nx
    lazy val y = ny
    lazy val z = nz
    if (x <= y ) y
    else tarai (
      tarai(x - 1, y, z),
      tarai(y - 1, z, x),
      tarai(z - 1, x, y)
    )
  }

  println(tarai(
    {println("call"); 100},
    {println("call"); 50},
    {println("call"); 0}
  ))
}
