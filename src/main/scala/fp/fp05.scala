package fp


/**
  * https://juejin.cn/post/7071225324033441805
  */
object fp05 {
  def main(args: Array[String]): Unit = {

//    val parThreeMult = (8, _: Int, 42) =
//    println(s"parThreeMult is ${parThreeMult}")
//    println(s"parThreeMult is ${parThreeMult(1)}")


//    val x = Cons(()=>{println("init x(0)");10},()=> Stream.empty)
  }
}
import fp.Stream._

sealed trait Stream[+A] {

  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }

  def toList: List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => h() :: t().toList
  }

  def take(n : Int) : Stream[A] = this match {
    case Cons(h, t) if n > 0 => cons(h(), t().take(n -1))
    case _ => empty
  }

  def drop(n : Int) : Stream[A] = ???

  def takeWhile(p : A => Boolean) : Stream[A] = ???

  def forAll(p : A => Boolean) : Boolean = ???

  //  def toList: List[A] =
//  @annotation.tailrec
//  def go(ll: LazyList[A], acc: List[A]): List[A] = ll match
//  case Cons(h, t) => go(t(), h() :: acc)
//  case Empty => acc.reverse
//  go(this, Nil)


  def unfold[A,S](z : S)(f : S=>Option[(A,S)]) : Stream[A] = f(z) match {
    case Some((h,s)) => cons(h,unfold(s)(f))
    case None => empty
  }

  def unfold0[A](a: A)(f: A => Option[A]) =
    unfold(a)(a => f(a).map(a => (a, a)))

  def from1(i: Int): Stream[Int] = unfold(true)(Some(i + 1, _))

}

case object Empty extends Stream[Nothing]

case class Cons[+A] (h: () => A , t: () => Stream[A]) extends Stream[A]




import scala.collection.immutable.Stream.cons

object Stream {
  def cons[A] (hd: => A , tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A] (as: A*): Stream[A] =
    if(as.isEmpty) empty else cons(as.head, apply(as.tail: _*))




}

object x {

  trait RandomNrGen {
    def nextInt : (Int,RandomNrGen)
  }

  case class SimpleRandomNrGen(seed : Long) extends RandomNrGen {
    private val nexus: Long = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFL
    private lazy val nextRandomNrGen: SimpleRandomNrGen = SimpleRandomNrGen(nexus)
    override def nextInt: (Int, RandomNrGen) = ((nexus >>> 16).toInt,nextRandomNrGen)

  }



  type Rdm[+A] = RandomNrGen => (A,RandomNrGen)
  def unit[A](a: A): Rdm[A] = randomNrGen => (a, randomNrGen)

//  def map[A, B](rdm: Rdm[A])(f: A => B): Rdm[B] = rdmNrGen =>
//  val (a, nxt) = unit(rdmNrGen)
//  (f(a), nxt)
}

