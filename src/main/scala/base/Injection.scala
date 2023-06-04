package base

object Injection {

  trait TradeRepository {
    def fetch(refNo: String): Trade
  }

  final case class Trade(
                          refNo: String,
                          tradeName: String,
                        )

  trait TradeService {
//    val withTrade = for {
//      t <- fetchTrade
//    } yield (t map n)

    protected val fetchTrade: TradeRepository => String => Trade ={
      repo => refNo => repo.fetch(refNo)
    }
  }

  class MockTradeRepository extends TradeRepository {
    override def fetch(refNo: String): Trade = {
      Trade("1", "Mock")
    }
  }

  object TradeServiceWithMock extends TradeService {
    val fetchTrade_c = fetchTrade(new MockTradeRepository)
  }



  def main(args: Array[String]): Unit = {
    val result = TradeServiceWithMock.fetchTrade_c("")
    println(s"result is ${result}")
  }


}
