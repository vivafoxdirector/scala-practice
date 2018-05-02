package io.yian.traitwith

trait Developer {
  def coding = println("Coding....")
}
trait Designer {
  def design = println("Designing...")
}
class Staff(name : String) extends Developer
/************************************/
trait ProjectManager {
  val budget:Int = 10000000
  println("budget=" + budget)
  def manage = println("ProjectManager...")
}
/************************************/
class Parent {
  println("Parent")
}

class Child extends Parent {
  println("Child")
}

trait A {
  println("trait A")
}
trait B {
  println("trait B")
}
trait C {
  println("trait C")
}
/************************************/
trait Author {
  def write1 = println("Author...1")
  def write2 = println("Author...2")
  def write3 = println("Author...3")
}
trait Writer {
  def write1 = println("Writer...1")
  def write2 = println("Writer...2")
  def write3 = println("Writer...3")
}
// class Reporter extends Author with Writer  // => error occured
class Reporter extends Author with Writer {
  override def write1 = println("Repoter..1")
  override def write2 = super.write2
  override def write3 = super[Author].write3
}
/************************************/
trait Document {
  def write
}
/************************************/
trait T1 {
  def t1 = println("t1")
}
trait T2 {
  def t2 = println("t2")
}
/************************************/
trait K1 {
  def write
}
trait K2 {
  def record
}
class K extends K1 with K2 {
  def write = println("write")
  def record = println("record")
}
/************************************/
abstract class Engineer {
  println("class Engineer constructor")
  def work(time:Int)
}

class Member extends Engineer {
  println("class Person constructor")

  def work(time:Int) = {
    println("Member#work start")
    println("One task"+time+" started...")
    println("Member#work end")
  }
}
/************************************/
trait Coder extends Engineer {
  println("trait Coder constructor")

  abstract override def work(time:Int) = {
    println("Coder#work start")
    super.work(time - 15)
    println("Coder#work end")
  }
}
/************************************/
trait Programmer {
  val name = "Yian"

  def sayName() : Unit = {
    println(s"My name is $name")
  }
}
class Person extends Programmer
/************************************/
trait Human {
  val name : String
}

trait Job {
  val job : String
}

trait Grade {
  val grade : String
}

class Man(n:String, j:String, g:String) extends Human with Job with Grade {
  val name = n
  val job = j
  val grade = g

  def say():Unit = {
    println(s"name: $name, job: $job, grade: $grade")
  }
}
/************************************/
trait Agiler extends Engineer {
  println("trait Agiler constructor")

  abstract override def work(time:Int) = {
    println("Agiler#work start")
    super.work(time/ 2)
    println("Agiler#work end")
  }
}
/************************************/
class Employee {
  println("Employee")
}
trait JuniorEmployee extends Employee {
  println("JuniorEmployee")
}
trait SeniorEmployee extends Employee {
  println("SeniorEmployee")
}
trait Manager extends SeniorEmployee {
  println("Manager")
}

class Worker extends Employee with JuniorEmployee with Manager

/*
Employee: Employee->AnyRef->Any
JuniorEmployee: JuniorEmployee->Employee->AnyRef->Any
SeniorEmployee: SeniorEmployee->Employee->AnyRef->Any
Manger: Manager->SeniorEmployee->Employee->AnyRef->Any
Worker: Worker->Manager->SeniorEmployee->JuniorEmployee->Employee->AnyRef->Any
*/

/************************************/
object Trait extends App {
  val d = new Staff("Yian") with Designer
  d.coding
  d.design
  println("--------------------------------")
  val pm = new Staff("Yian") with ProjectManager
  println("--------------------------------")
  val c = new Child with A with B with C
  println("--------------------------------")
  val r = new Reporter
  r.write1
  r.write2
  r.write3
  println("--------------------------------")
  val a = new Author{}
  a.write1
  println("--------------------------------")
  val dc = new Document{def write = println("Document...")}
  dc.write
  println("--------------------------------")
  val t = new T1 with T2
  t.t1
  t.t2
  println("--------------------------------")
  val m = new Member
  m.work(60)
  println("--------------------------------")
  val co = new Member with Coder
  co.work(60)
  println("--------------------------------")
  val p = new Person
  p.sayName
  println("--------------------------------")
  val man = new Man("Yian", "Developer", "1")
  man.say
  println("--------------------------------")
  val pe = new Member with Coder with Agiler
  pe.work(60)
  println("--------------------------------")
  val w = new Worker
}
