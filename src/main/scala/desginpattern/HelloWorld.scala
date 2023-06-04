package desginpattern

import desginpattern.HelloDSL.{Group, Role, User}

object HelloDSL extends App {

  case class Rule(
                   u: User,
                   role: Role,
                   hasOrNot: Boolean
                 ) {

    def verify(user: User, role: Role): Boolean = {
      println(s"user is ${user.name}, ${user.groups}")
      if (user.groups.contains(u.groups.head)
        && this.role.name == role.name
      ) hasOrNot
      else true
    }

  }

  case class User(
              name: String,
              groups: Seq[Group] = Seq.empty){
//    var groups: Seq[Group] = Seq.empty

    def in(group: Group): User = {
      //this.groups = groups ++ Seq(group)
      this
    }

    def no(role:Role) = {
      Rule(this, role, false)
    }

    def have() = false
  }

  case class Group(name:String){}

  case class Role(name : String){}

  case object people {
    def in(group: Group): User = {
      //this.groups = groups ++ Seq(group)
      new User("all", Seq(group))
    }
  }



  object TOR extends Group("tor")

  object prodW extends Role("write")

  override def main(args: Array[String]): Unit = {

    val rule1 = people in Group("tor") no Role("write")

    val rule1Rs = rule1.verify(new User("tom", Seq(TOR)), prodW)

    println(s"verify rule 1 expect false, actual: ${rule1Rs}")
  }
}


trait TaxFeeRulesComponent {
//  val forHK : PartialFunction[Market, List[TaxFee]] = {
//    case HK => List()
//  }
}