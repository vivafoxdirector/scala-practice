//package io.yian.generic

class Base
class Ex1 extends Base
class Ex2 extends Ex1

class MySample1[Ex2]
class MySample2[A <: Ex1]
class MySample3[A >: Ex1]

class MySample4[A >: Ex1 <: Base]

class MySample5[A <: { def doit():Unit}](val a:A) {
  def get:A = this.a
}
class Foo{ def doit():Unit = println("doit!")}

class Ex3 extends Base
class Foo2[T]

class Foo3[+T]

class Foo4[-T]

object Generic extends App {
  new MySample1[Ex2]

  //new MySample2[Base] //==> Error
  new MySample2[Ex1]
  new MySample2[Ex2]

  new MySample3[Ex1]
  new MySample3[Base]

  new MySample4[Ex1]

  val f = new Foo
  val m = new MySample5[Foo](f)
  m.get.doit

  // Invariant Test
  def invariant(arg:Foo2[Base]) = println("Ok")
  //invariant(new Foo2[Ex3])  // ==> Error

  // Covariant Test
  def covariant(arg:Foo3[Base]) = println("OK")
  covariant(new Foo3[Ex3])

  // Contravariant Test
  def contravariant(arg:Foo4[Ex3]) = println("OK")
  contravariant(new Foo4[Base])
}
