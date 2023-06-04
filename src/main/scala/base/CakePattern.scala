package base


object CakePattern {

  final case class Employee(name: String)

  trait EmployeeDbComp {

    val employeeDb: EmployeeDb

    trait EmployeeDb {
     // def createEmployee(employee: Employee)
      def getEmployee(empId: Int): Employee
    }

  }

  trait MockEmployeeDbComp extends EmployeeDbComp {

    this:EmployeeDbComp =>
    val employeeDb = new MockEmployeeDb

    class MockEmployeeDb extends EmployeeDb {

    //  override def createEmployee(employee: Employee): Unit = ???

      override def getEmployee(empId: Int): Employee = Employee("Mock")
    }
  }


  trait EmployeeServiceComp {

    val service: EmployeeService

    trait EmployeeService {
      //def POST(employee: Employee): Future[Unit]
      def GET(id: Int): Employee
    }

  }

  trait SprayEmployeeServiceCompImpl extends EmployeeServiceComp {

    this:EmployeeServiceComp =>

    val service = new SprayEmployeeService

    class SprayEmployeeService extends EmployeeService {

      override def GET(id: Int): Employee = {
        Employee("Tom")
      }
    }
  }

  class EmployeeHandler {

    this: EmployeeDbComp with EmployeeServiceComp =>

    def findEmployee(empId: Int): Employee = {
      //service
      service.GET(empId)
      // find employee via db
      employeeDb.getEmployee(empId)
    }
  }

  val employeeHandler = new EmployeeHandler
    with MockEmployeeDbComp
    with SprayEmployeeServiceCompImpl

  def main(args: Array[String]): Unit = {
    val r = employeeHandler.findEmployee(1)
    println(s"result is $r")
  }
}
