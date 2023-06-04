package base

/**
  * https://cloud.tencent.com/developer/article/1013586
  */
object StateTest {

  type Stack = List[Int]
  def pop: State[Stack, Int] = State[Stack, Int]{ case x::xs => (x, xs) }
  def push(i: Int):State[Stack, Unit] = State[Stack, Unit]{ case xs => ((), i :: xs ) }

  def stackRun: State[Stack, Int] = {
         for {
               _ <- push(13)
               a <- pop
               b <- pop
         } yield a+b
  }

  def main(args: Array[String]): Unit = {
    val (a, s) =stackRun.run(List(10,11,12))
    println(a,s)

  }



}

case class State[S,+A](run: S => (A, S)) {
  import State._

  def flatMap[B](f: A => State[S, B]): State[S, B] = State[S, B] {
    s => {
      val (a, s1) = run(s)
      f(a).run(s1)
    }
  }

  def map[B](f: A => B): State[S,B] = State[S,B] {
    s => {
                   val (a, s1) = run(s)
      (f(a),s1)
    }
  }

  def map_1[B](f: A => B): State[S,B] = {
             flatMap { a => unit(f(a)) }
  }

  def map2_1[B,C](sb: State[S,B])(f: (A,B) => C): State[S,C] ={
             for {
                   a <- this
                   b <- sb
               } yield f(a,b)
  }
}

object State {
  def unit[S,A](a: A) = State[S,A](s => (a, s))
}
