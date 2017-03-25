# SparkTraining
Sandbox for learning Spark 2.1 using resources from Pluralsight and Learning Spark (O'Reilly)

#### Scala Environment:
* Intellij IDEA
* Maven
* Scala 2.11
* Spark 2.1

#### Python Environment:
* PyCharm
* Python 3.5
* Spark 2.1

## Setting up PySpark
1. Install Java 1.8 and Python 3
2. Install Scala 2.11
3. Download pre-built Spark 2.1
4. Install py4j (I used PyCharm's repository search)
5. Add following to '~/.bash_profile'
    'export SPARK_HOME=/My/Spark/Directory/Spark'
    'export PYTHONPATH=$SPARK_HOME/python'
6. In PyCharm, add 'SPARK_HOME/python/' to interpreter path

### Initialize PySpark shell
Open terminal, cd to SPARK_HOME directory, then enter './bin/pyspark --master local[2]'
Note that '[2]' represents the # of cores used