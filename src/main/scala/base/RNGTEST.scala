package base


object RNGTEST {


  def main(args: Array[String]): Unit = {
    import RNG._

    val rng = seedRNG(System.currentTimeMillis)

    rng.nextInt
    rnPositiveInt(rng)

    println(s"rnDouble(rng) ==> ${rnDouble(rng)}")
    println(s"rnInt(rng)) ==> ${rnInt(rng)}")

    println(s"rnIntDoublePair(rng)) ==> ${rnIntDoublePair(rng)}")
    println(s"rnDoubleIntPair(rng)) ==> ${rnDoubleIntPair(rng)}")
  }

  trait RNG {
    def nextInt: (Int, RNG)
  }

  //起始状态RNG, 种子RNG
  case class seedRNG(seed: Long) extends RNG {
    def nextInt: (Int, RNG) = {
      val seed2 = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      ((seed2 >>> 16).asInstanceOf[Int], seedRNG(seed2))
    }
  }

  object RNG {
    type Rand[+A] = RNG => (A, RNG)

    def nextDouble(rng: RNG): (Double, RNG) = {
      val (i, rng2) = rng.nextInt
      if (i == Int.MaxValue) (0.0, rng2)
      else (i.toDouble / Int.MaxValue.toDouble, rng2)
    }

    def nextPositiveInt(rng: RNG): (Int, RNG) = {
      val (i, rng2) = rng.nextInt
      if (i == Int.MaxValue) (Int.MaxValue, rng2)
      else (i.abs, rng2)
    }

    def rnInt: RNG => (Int, RNG) = (rng) => rng.nextInt

    def rnInt2: Rand[Int] = _.nextInt

    def rnPositiveInt: Rand[Int] = nextPositiveInt

    def rnDouble: RNG => (Double, RNG) = {
      map(rnPositiveInt) {
        _ / (Int.MaxValue.toDouble + 1)
      }
    }

    def rnDouble2: RNG => (Double, RNG) = {
      map(rnPositiveInt) {
        _ / (Int.MaxValue.toDouble + 1)
      }
    }

    def map[A, B](ra: RNG => (A, RNG))(f: A => B): Rand[B] = {
      rng => {
        val (x, rng2) = ra(rng)
        (f(x), rng2)
      }
    }

    def flatMap[A, B](ra: Rand[A])(f: A => Rand[B]): Rand[B] = {
      rng => {
        val (x, rng2) = ra(rng)
        f(x)(rng2)
      }
    }

    val t = Seq(1,2,3)
    t.map(_ + 1)
    t.flatMap( e => Seq(e))


    def unit[A](a: A): Rand[A] = {
      rng => (a, rng)
    }

    def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
      rng => {
        val (x, rng2) = ra(rng)
        val (y, rng3) = rb(rng2)
        (f(x, y), rng3)
      }
    }

    def rnPair[A, B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] = {
      map2(ra, rb) {
        (_, _)
      }
    }

    def rnIntDoublePair: Rand[(Int, Double)] = {
      rnPair(rnInt, rnDouble)
    }

    def rnDoubleIntPair: Rand[(Double, Int)] = {
      rnPair(rnDouble, rnInt)
    }

    def positiveIntByFlatMap: Rand[Int] = {
      flatMap(rnInt) {
                     a => {
                           if ( a != Int.MinValue) unit(a.abs)
                           else positiveIntByFlatMap
                       }
                 }
    }

    def mapByFlatMap[A,B](ra: Rand[A])(f: A => B): Rand[B] = {
            flatMap(ra){ a => unit(f(a)) }
    }


    //val (d, rng5) = nextDouble(rng)


  }

}

