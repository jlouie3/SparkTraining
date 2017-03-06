# Notes

## RDD's

* Partitioned across nodes
  * Each node has 1 partition. 
  * Can hold RDD in memory as long as size of data
    does not surpass total memory of all nodes in cluster
* Read-only/immutable
* Only 2 types of operations
  * Transformations
    * Examples: loading, filtering, sorting, any method that alters the data in an RDD
    * Input: RDD, Output:RDD
    * Lazily executed, thus multiple transformations on a single RDD are "queued up" until execution is called
  * Actions 
    * Examples: Reads, counts, sums, any method that demands an actual value
    * An action all transformations in queue to execute
*Lineage: RDD knows where it came from. Each RDD only contains metadata until an action is called, at which point
    the final RDD will materialize. Lineage is traced back to the source which allows RDD's to have resilience and
    lazy evaluation.

### Operations

Operation | Type | Description
--- | --- | --- |
Filter | Transform | Transform an RDD by selecting which records remain/are removed based on TRUE/FALSE result of a specified function
Map | Transform | Transform one record into another record- input/output maintains a 1:1 relationship
Reduce | Action | Requires 2 arguments and a function to aggregate multiple records and reduce the total number of records in the RDD
  
## Resources Used
* [Scala OOP Features Explained] (http://www.vasinov.com/blog/scala-oop-galore/)
* [Learning Spark: Lightning-Fast Big Data Analysis (O'Reilly, 2015)] (https://www.amazon.com/Learning-Spark-Lightning-Fast-Data-Analysis/dp/1449358624)
* [Pluralsight: Beginning Data Exploration and Analysis with Apache Spark] (https://www.pluralsight.com/courses/apache-spark-beginning-data-exploration-analysis)
