package fp

object fp02 {
  def fib(n: Int) : Int = {
    n match {
      case 0 => 0
      case 1 => 1
      case _ => fib(n - 1) + fib(n - 2)
    }
  }

  /**
    * 2.4.2 HOF
    */
  def formatResult(name: String, n: Int, f: Int => Int) = {

  }

  /**
    * 2.5 多态函数
    * * 非面向对象的OO中的多态，只是函数的参数化
    */
  def findFirst[A](as : Array[A], p: A => Boolean): Int = ???

  def isSorted[A] (as : Array[A], ordered:(A,A) => Boolean):Boolean = ???

  /**
    * 2.5.2 对高阶函数传入匿名函数
    * Q：函数字面量？
    */
  def partial1[A,B,C] (a: A, f: (A,B) => C) : B => C = (b:B) => f(a,b)

  def curry[A,B,C](f: (A,B) => C) : A => (B => C) = ???
}
