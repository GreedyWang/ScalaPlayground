package fp

/**
  * 函数式数据结构
  */
object fp03 {
  /**
    * todo：scala trait vs abstrace
    * mng.bz/R75t
    *
    * case object有啥用
    */

  def max3(): Unit = {

  }

  val max4 = max3 _

  def main(args: Array[String]): Unit = {
    println(s"max4 is ${max4}")
  }
}
