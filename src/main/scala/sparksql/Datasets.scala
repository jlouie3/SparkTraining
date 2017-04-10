package sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import main.scala.sparksql.model._
/**
  * Created by louie on 3/28/2017.
  */
object Datasets {
  def main(args: Array[String]): Unit= {
    // Spark Config
    val appName = "Basic Transforms"
    val masterURL = "local[2]"
    val memAlloc = "1g"
    val conf = new SparkConf().setAppName(appName).setMaster(masterURL).set("spark.executor.memory", memAlloc)
    val spark = SparkSession.builder().config(conf).getOrCreate()

    // Allow use of case classes
    import spark.implicits._

    // Create EMPLOYEES dataset
    val employees = spark.read.option("header", "true").option("inferSchema","true")
      .csv("data/NW-Employees.csv").as[Employee]
    println("employees has " + employees.count() + " rows")

    // Create ORDER DETAILS table
    val orderDetails = spark.read.option("header","true").option("inferSchema","true").
      csv("data/NW-Order-Details.csv").as[OrderDetails]
    println("Order Details has "+orderDetails.count()+" rows")

    // Drop a column
    println("Drop the LastName column:")
    employees.show()
    employees.drop("LastName").show()

    // Select only certain columns
    employees.select(employees("LastName"), employees("FirstName"), employees("BirthDate"))

    // Cast Qty column from INT to DOUBLE
    println("Cast the Qty column:")
    val qty_double = orderDetails("Qty").cast("double").alias("Qty_double")
    orderDetails.select(orderDetails("*"), qty_double).show()

    """
      | Scientific Functions
      | import org.apache.spark.sql.functions
      |   log()
      |   log10()
      |   sqrt()
      |   cbrt()
      |   exp()
      |   pow()
      |   sin()
      |   cos()
      |   tan()
      |   acos()
      |   asin()
      |   atan()
      |   toDegrees()
      |   toRadians()
    """.stripMargin

    """
      | Aggregate Functions
      |   groupBy()
      |   min()
      |   max()
      |   avg()
      |   mean()
      |   std()
      |   agg()
    """.stripMargin

    // Write dataset to disk
    // csv format with headers
    employees.write.mode("overwrite").option("header","true").
      csv("data/employees-out-csv.csv")

    // Write modes: overwrite, ignore (do not write if file exists), append, error (raise exception if file exists)

  }
}
