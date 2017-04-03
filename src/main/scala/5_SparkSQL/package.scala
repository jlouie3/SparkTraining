import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import Employee._
/**
  * Created by louie on 3/28/2017.
  */
object SparkSQL {
  def main(args: Array[String]): Unit= {
    // Spark Config
    val appName = "Basic Transforms"
    val masterURL = "local[2]"
    val memAlloc = "1g"
    val conf = new SparkConf().setAppName(appName).setMaster(masterURL).set("spark.executor.memory", memAlloc)
    val spark = SparkSession.builder().config(conf).getOrCreate()

    // Create EMPLOYEES dataframe
    val filePath = "data/NW-Employees.csv"
    val employees = spark.read.option("header", "true").csv(filePath).as[Employee]
    println("employees has " + employees.count() + " rows")

    // Create a queryable EMPLOYEES view
    employees.createOrReplaceTempView("EmployeesTable")

    // Use SparkSQL to query new EMPLOYEERS view
    var result = spark.sql("SELECT * from EmployeesTable")
    result.show(5)
    employees.explain(true)

    // Query with filter
    result = spark.sql("SELECT * from EmployeesTable WHERE State = 'WA'")
    result.show(5)

    // Create a second table called ORDERS
    val orders = spark.read.option("header","true").csv(filePath + "data/NW-Orders.csv").as[Order]
    println("Orders has " + orders.count() + " rows")
    orders.createOrReplaceTempView("OrderssTable")

    // Import ORDERS by inferring schema
    val orders_inferred = spark.read.option("header","true").option("inferSchema","true").
      csv(filePath + "data/NW-Orders.csv").as[Order]
    println("Orders has "+orders_inferred.count()+" rows")
    orders_inferred.show(5)
    orders_inferred.head()
    orders_inferred.dtypes // verify column types

    // Create ORDER DETAILS table
    val orderDetails = spark.read.option("header","true").option("inferSchema","true").
      csv(filePath + "data/NW-Order-Details.csv").as[OrderDetails]
    println("Order Details has "+orderDetails.count()+" rows")
    orderDetails.show(5)
    orderDetails.head()
    orderDetails.dtypes // verify column types
    orderDetails.createOrReplaceTempView("OrderDetailsTable")

    // More interesting queries....
    result = spark.sql(
      """SELECT OrderDetailsTable.OrderID, ShipCountry, UnitPrice, Qty, Discount
        FROM OrdersTable
        INNER JOIN OrderDetailsTable
        ON OrdersTable.OrderID = OrderDetailsTable.OrderID""".stripMargin)
    result.show(10)
    result.head(3)

    // Sales By Country
    result = spark.sql(
      """SELECT ShipCountry, SUM(OrderDetailsTable.UnitPrice * Qty * Discount) AS ProductSales
        FROM OrdersTable
        INNER JOIN OrderDetailsTable
        ON OrdersTable.OrderID = OrderDetailsTable.OrderID
        GROUP BY ShipCountry""")
    result.count()
    result.show(10)
    result.head(3)
    result.orderBy("ProductSales", "desc").show(10) // THIS LINE MIGHT BE INCORRECT
  }
}
