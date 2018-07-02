val f(a:Int, b:Int) = a.toString + b.toString
def f(a:Int, b:Int) = a.toString + b.toString
f(1,2)
def f:(Int,Int) => String
def f = (a:Int, b:Int) => a.toString + b.toString
f(1,2)
val f:Function1[Int, String] = new Function1[Int, String] {
def apply(arg:Int):String = arg.toString
}
f.apply(10)
import java.io._
val file = new File("amm_history.scala")
val bw = new BufferedWriter(new FileWriter(file))
val history = repl.history.mkString("\n")
bw.write(history)
bw.close()
