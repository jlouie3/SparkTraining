
/**
  * Created by louie on 3/27/2017.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object LoadDataIntoRDD {
  def main(args: Array[String]) : Unit = {
    // Spark Config
    val appName = "Basic Transforms"
    val masterURL = "local[2]"
    val memAlloc = "1g"
    val conf = new SparkConf().setAppName(appName).setMaster(masterURL).set("spark.executor.memory", memAlloc)
    val spark = SparkSession.builder().config(conf).getOrCreate()

    // Get SparkContext from SparkSession
    val sc = spark.sparkContext

    // Can convert a Scala collection in an RDD using sc.parallelize()
    val list = List(1, 2, 3)
    val myRDD = sc.parallelize(list)

    // Many ways to get data from file into RDD
    val filePath = "data/Line_of_numbers.csv"

    // Get RDD from file
    val inFile = sc.textFile(filePath)
    // Get single line of comma-separated numbers and load them into RDD
    val numbersRDD = inFile.map(line => line.split(','))      // Split elements in line into array
    // Split each element in line into own RDD row, then convert to numeric type
    val numbersRDD2 = inFile.flatMap(line => line.split(',')).map(x => x.toDouble)
    // Add row number to each row in RDD, then put row ID first in tuple
    val keyValueRDD = numbersRDD2.zipWithIndex().map(x => x.swap)

    /* Loading a Sequence File
      Sequence files are binary flat files that consist of key-value pairs; they are one of the
      common ways of storing data for use with Hadoop. Loading a sequence file into Spark is
      similar to loading a text file, but you also need to let it know about the types of keys and
      values. The standard WritableConverter types are integer, long, double, float, Boolean, byte array,
      and string.

    val data = sc.sequenceFile[String, Int](inputFile)
    */

    /* Can also read from HBase, but look up in book or other source */

    // Saving Data
    numbersRDD2.saveAsTextFile("filePath.txt")  // As text file
    keyValueRDD.saveAsObjectFile("sequenceOut") // As object file

  }
}
