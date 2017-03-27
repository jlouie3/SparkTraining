/**
  * Created by louie on 2/20/2017.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

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

    //////////////////////////////////////////////////////////////////
    // REPEAT ABOVE BUT USE SPARKSESSION AND DATAFRAME IN LIEU OF RDD
    //////////////////////////////////////////////////////////////////

    // Import .csv file to dataframe object
    val spark = SparkSession.builder().appName(appName).getOrCreate()
    val df_apple = spark.read.option("header", value = true).csv(filePath)

    // Basic dataframe actions
    val ten_rows = df_apple.take(10)  // Take top 10 rows and then print 1 on each line
    ten_rows.foreach(println)
    println(df_apple.first())         // Take top row and print it

  }
}