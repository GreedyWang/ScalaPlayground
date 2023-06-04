package fp
//https://www.scala-exercises.org/fp_in_scala/purely_functional_parallelism
object fp07 {

  def target(): Unit = {
//    val outputList = parMap(inputList)(f)
  }

  //use case 01: sum
  def sum(ints: IndexedSeq[Int]): Int = {
    if (ints.size <= 1) ints.headOption.getOrElse(0)
    else {
      val (l,r) = ints.splitAt(ints.length / 2)
      val sumL: Par[Int] = Par.unit(sum(l))
      val sumR: Par[Int] = Par.unit(sum(r))
      Par.get(sumL) + Par.get(sumR)
    }
  }

  def sum2(ints: IndexedSeq[Int]): Par[Int] = {
    if (ints.size <= 1) Par.unit(ints.headOption.getOrElse(0))
    else {
      val (l,r) = ints.splitAt(ints.length / 2)
      Par.map2(sum2(l), sum2(r))(_ + _)
    }
  }

  abstract class Par[A] {

  }
  object Par {
    def unit[A](a: => A): Par[A] = ???
    def get[A](a: Par[A]): A = ???
    def map2[A](a: Par[A], b: Par[A])(f: (A,A) => A): Par[A] = ???
  }
//  object Par {
//
//  }

}
