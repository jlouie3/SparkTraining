import os
import sys

if 'SPARK_HOME' not in os.environ:
    os.environ['SPARK_HOME'] = '/Users/jlouie/Development/SDKs/Spark-2.1'

if '/Users/jlouie/Development/SDKs/Spark-2.1/python' not in sys.path:
    sys.path.insert(0, '/Users/jlouie/Development/SDKs/Spark-2.1/python')

from pyspark.sql import SparkSession

appName = 'PySparkTemplate'
sparkSession = SparkSession.builder.appName(appName).getOrCreate()
