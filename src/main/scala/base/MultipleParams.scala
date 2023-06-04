package base

/**
  * They let you have both implicit and non-implicit parameters
  * They facilitate type inference
  * A parameter in one group can use a parameter from a previous group as a default value
 */
object MultipleParams {
  def whilst(testCondition: => Boolean)(codeBlock: => Unit) = ???
  //Using by-name parameters?
}

/**
  * From the book, Beginning Scala, by David Pollak.
  */
object Control {

  //A can be an instance of any class that has a close() method. (This type of definition is known as a structural type in Scala.)
  def using[A <: { def close(): Unit }, B](param: A)(f: A => B): B =
    try {
      f(param)
    } finally {
      param.close()
    }

}

//There are analytical techniques that can only be applied to functions with a single argument.
// Practical functions frequently take more arguments than this.
object Currying {
  // Partially-applied functions benefit
  //1.it’s a way to create specialized methods from more general methods.
}