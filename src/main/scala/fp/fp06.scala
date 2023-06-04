package fp

class fp06 {


  sealed trait Input
  case object Coin extends Input
  case object Turn extends Input

  case class Machine(locked: Boolean, candies: Int, coins:Int) {
    //def simulateMachine(inputs: List[Input]): State[Machine, (Int,Int)] = ???

    def putCoin(): Machine = {
      if (locked == false) this
      else Machine(false, candies = candies - 1, coins = coins + 1)
    }

    def getCandy(): Unit ={
      if (locked) this
      else Machine(false, candies = candies - 1, coins = coins + 1)
    }


    def while_(condition: => Boolean,action : => Unit): Unit = {
      if (condition) {
        action
        while_(condition, action)
      }
    }


  }
}
