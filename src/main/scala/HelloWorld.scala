/**
  * Created by louie on 2/20/2017.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object HelloWorld {
  def main(args: Array[String]) : Unit = {
    // Spark Config
    val appName = "Hello World"
    val masterURL = "local[2]"
    val memAlloc = "1g"
    val conf = new SparkConf().setAppName(appName).setMaster(masterURL).set("spark.executor.memory",memAlloc)
    val sc = new SparkContext(conf)

    // Paths and logging
    val projectPath = System.getProperty("user.dir")
    val logFile = projectPath + "\\logfile.txt"

    println("Hello World")
    // Import CSV data to RDD
    val filePath = projectPath + "\\data\\apple.csv"
    val apple = sc.textFile(filePath)

    // Basic RDD actions
    println("Top 10 rows in RDD: ")
    apple.take(10).foreach(println)
    println("Top row in RDD: ")
    println(apple.first())
    println("Take all rows and put in an array: ")
    apple.collect().foreach(println)

  }
}