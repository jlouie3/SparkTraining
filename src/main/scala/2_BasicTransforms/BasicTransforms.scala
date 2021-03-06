/**
  * Created by louie on 3/1/2017.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object BasicTransforms {
  def main(args: Array[String]) : Unit = {
    // Spark Config
    val appName = "Basic Transforms"
    val masterURL = "local[2]"
    val memAlloc = "1g"
    val conf = new SparkConf().setAppName(appName).setMaster(masterURL).set("spark.executor.memory",memAlloc)
    val sc = new SparkContext(conf)

    // Paths and logging
    val projectPath = System.getProperty("user.dir")
    val logFile = projectPath + "\\logfile.txt"

    // Import CSV data to RDD
    val filePath = projectPath + "\\data\\nypd_7_major_felony_incidents.csv"
    val data = sc.textFile(filePath)

    // Remove header row from RDD
    val header = data.first()
    println("First record is header: ")
    println(header)
    val dataWithoutHeader = data.filter(row => !row.equals(header))
    println("Header has been removed: ")
    println(dataWithoutHeader.first())

    // persist() allows RDD to call an action more than once by storing it (in memory or on disk)
    dataWithoutHeader.persist()
    val dataAsListRDD = dataWithoutHeader.map(x => x.split(",")).take(10)
    val count = dataWithoutHeader.count()

    /////////////////////////////////////////////////////////////////
    // REPEAT ABOVE BUT USE SPARKSESSION AND DATAFRAME IN LIEU OF RDD
    //////////////////////////////////////////////////////////////////

    val spark = SparkSession.builder().appName(appName).getOrCreate()
    val df_data = spark.read.option("header", value = true).csv(filePath)
    df_data.persist()
    val count_df = df_data.count()
  }
}
