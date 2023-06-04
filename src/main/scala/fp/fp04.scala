package fp

class fp04 {

  //def lift[A,B] (f: A => B): Option[A] = Option[B] = _ map f
  def map2[A,B,C] (a: Option[A], b: Option[B]) (f: (A,B) => C) : Option[C] =
//    a flatMap( aa =>
    a flatMap ( aa =>
      b map (bb =>
        f(aa, bb)
        )
      )



}

//sealed trait MyOption[+A]
//case class MySome[+A](get: A) extends MyOption[A]


