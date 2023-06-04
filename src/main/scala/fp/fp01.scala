package fp

object fp01 {
//Q1: 必须存在副作用的函数对应的函数式实现，
  // 和没有对应的fp实现是如何让副作用发生但不可见

  /**
    * 3.2 Scala的数据结构都是利用数据共享来实现。
    * Vector的random access，update都是常量级别的
    */

  /**
    * 高阶函数的类型推导
    */
  def dropwhile[A] (l: List[A], f: A => Boolean) : List[A] = ???



  def dropwhile2[A] (l: List[A])(f: A => Boolean) : List[A] = ???

  val xs = List(1,2,3,4)
  dropwhile(xs, (x:Int) => x<4)
  dropwhile2(xs)(x => x<4)
}
