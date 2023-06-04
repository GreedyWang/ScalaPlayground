package fp

object fp_pre {

  def while_(condition: => Boolean,action : => Unit): Unit = {
    if (condition) {
      action
      while_(condition, action)
    }
  }

  // : => Boolean
  //This is called call-by-name. It means when you call the method, the argument is not evaluated before the method is executed, but rather,
  // it is evaluated each time it is referred to inside the method.
  def while2_(condition: => Boolean)(action : => Unit): Unit = {
    if (condition) {
      action
      while2_(condition)(action)
    }
  }
  var i = 1
  var sum = 0
  while2_(i<=10){
    sum += i
    i+=1
  }

  while_({i <= 10},{ sum += i;i+=1})

}
