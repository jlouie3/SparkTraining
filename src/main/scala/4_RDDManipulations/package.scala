
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
/**
  * Created by louie on 3/28/2017.
  */
object RDDManipulations {
  def main(args: Array[String]): Unit= {
    // Spark Config
    val appName = "Basic Transforms"
    val masterURL = "local[2]"
    val memAlloc = "1g"
    val conf = new SparkConf().setAppName(appName).setMaster(masterURL).set("spark.executor.memory", memAlloc)
    val spark = SparkSession.builder().config(conf).getOrCreate()

    // Get SparkContext from SparkSession
    val sc = spark.sparkContext
  }
}
