import os
import sys

if 'SPARK_HOME' not in os.environ:
    os.environ['SPARK_HOME'] = '/Users/jlouie/Development/SDKs/Spark-2.1'

if '/Users/jlouie/Development/SDKs/Spark-2.1/python' not in sys.path:
    sys.path.insert(0, '/Users/jlouie/Development/SDKs/Spark-2.1/python')

from pyspark.sql import SparkSession

appName = 'HelloWorld'
spark = SparkSession.builder.appName(appName).getOrCreate()

# Import .csv file to dataframe object
filePath = '../data/apple.csv'
filePath = '/Users/jlouie/Development/Projects/PySparkTraining/data/apple.csv'
df_apple = spark.read.option("header", True).csv(filePath)

# Basic dataframe actions
ten_rows = df_apple.take(10)    # Take top 10 rows and then print 1 on each line
for row in ten_rows:
    print(row)

print(df_apple.first())         # Take top row and print it
