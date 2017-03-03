import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object SparkTemplate {
  def main(args: Array[String]) : Unit = {
    // Spark Config
    val appName = "SparkTemplate"
    val masterURL = "local[2]"
    val memAlloc = "1g"
    val conf = new SparkConf().setAppName(appName).setMaster(masterURL).set("spark.executor.memory",memAlloc)
    val sc = new SparkContext(conf)

    // Paths and logging
    val projectPath = System.getProperty("user.dir")
    val logFile = projectPath + "\\logfile.txt"

    /* START CODE HERE */
  }
}