package io.yian.traitwith

import io.yian.common.ExamHolder

/**
  * ref: https://www.atmarkit.co.jp/ait/articles/1206/20/news137.html
  */
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
//trait Agiler extends Engineer {
//  println("trait Agiler constructor")
//
//  abstract override def work(time:Int) = {
//    println("Agiler#work start")
//    super.work(time/ 2)
//    println("Agiler#work end")
//  }
//}
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
object TraitTest {
  
  /**
    * 기본적인 트레이트 사용방법
    */
  def traitTest001() : Unit = {
    trait Programmer {
      def config = println("welcome trait!!")
    }
    class Person(val name:String) extends Programmer
    val p = new Person("yian")
    p.config
  }

  /**
    * 인스턴스 생성시 트레이트 mix-in
    * with 키워드를 사용하여 인스턴스 생성을 한다.
    */
  def traitTest002() : Unit = {
    trait Programmer {
      def config = println("welcome trait!!")
    }
    trait Design {
      def design = println("design...")
    }
    class Person(val name:String) extends Programmer

    // with 키워드 사용
    val p = new Person("yian") with Design
    p.design
    p.config
  }
  
  /**
    * 트레이트로 생성자 처리
    */
  def traitTest003() : Unit = {
    trait Programmer {
      def config = println("welcome trait!!")
    }
    class Person(val name:String) extends Programmer
    
    trait ProjectManager {
      val budget:Int = 10000000
      println("budget=" + budget)
      def manage = println("ProjectManager...")
    }
    
    // 인스턴스 생성만 해도 트레이트의 생성자가 호출된다.
    val pm = new Person("Yian") with ProjectManager
  }
  /**
    * 생성자 호출 순서
    * 부모 -> 자식 -> traitA -> traitB -> traitC
    */
  def traitTest004() : Unit = {
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

    val c = new Child with A with B with C
  }

  /**
    * 상속받는 여러 트레이트의 메소드명이 같으면 에러가 난다.
    * 이경우 override 키워드를 사용하여 메소드를 정의해야 한다.
    */
  def traitTest005() : Unit = {
    trait Programmer {
      def writer = println("coding..")
    }

    trait Writer {
      def writer : Unit = println("writing...")
    }

    // 같은 메소드 명을 override하지 않으면 에러가 난다.
    // class Person extends Programmer with Writer

    class Person extends Programmer with Writer {
      override def writer = println("writing...document")
    }

    val p = new Person
    p.writer
  }

  /**
   * Use [super] keyword
   */ 
  def traitTest006() : Unit = {
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
      // use method of Writer
      override def write2 = super.write2

      // specified trait as Author
      override def write3 = super[Author].write3
    }
    val r = new Reporter
    r.write1
    r.write2
    r.write3
  }

  /**
   * make instance of trait
   */
  def traitTest007() : Unit = {
    trait Author {
      def write1 = println("Author...1")
      def write2 = println("Author...2")
      def write3 = println("Author...3")
    }
    // make instance of trait
    val a = new Author{}
    a.write1
  }

  // 
  def traitTest008() : Unit = {
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
    val m = new Member
    m.work(60)
  }

  def traitTest009() : Unit = {
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
    trait Coder extends Engineer {
      println("trait Coder constructor")
    
      abstract override def work(time:Int) = {
        println("Coder#work start")
        super.work(time - 15)
        println("Coder#work end")
      }
    }
    val co = new Member with Coder
    co.work(60)
  }
    
  def traitTest0XX() : Unit = {
    trait Document {
      // abstract method
      def write
    }
    val dc = new Document{def write = println("Document...")}
    dc.write
  }
  /*
  println("--------------------------------")
  val t = new T1 with T2
  t.t1
  t.t2
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
  */
  def main(args: Array[String]) : Unit = {
    val a = new ExamHolder("Trait")
    a.addFunc("exerciseTrait001", "traitTest001", traitTest001())
    a.addFunc("exerciseTrait002", "traitTest002", traitTest002())
    a.addFunc("exerciseTrait003", "traitTest003", traitTest003())
    a.addFunc("exerciseTrait004", "traitTest004", traitTest004())
    a.addFunc("exerciseTrait005", "traitTest005", traitTest005())
    a.addFunc("exerciseTrait006", "traitTest006", traitTest006())
    a.addFunc("exerciseTrait007", "traitTest007", traitTest007())
    a.addFunc("exerciseTrait008", "traitTest008", traitTest008())
    a.addFunc("exerciseTrait009", "traitTest009", traitTest009())
    a.selectFunc
  }
}
