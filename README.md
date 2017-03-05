# SparkTraining
Sandbox for learning Spark 2.1 using resources from Pluralsight and Learning Spark (O'Reilly)

##### Environment: 
* Intellij IDEA
* Maven
* Spark 2.1
* Scala 2.11

#### Notes: 
* Reduce Spark console verbosity
  1. Make a copy of conf/log4j.properties.template called conf/log4j.properties 
  2. Find the following line: 'log4j.rootCategory=INFO, console'
  3. Lower the log level so only the WARN messages show by changing it to the following: 'log4j.rootCategory=WARN, console'
* To shut down Spark, call sc.stop() where sc is your SparkContext
