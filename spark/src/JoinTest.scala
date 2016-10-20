
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
object JoinTest {
  
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf
    conf.setMaster("local[2]").setAppName("WORDCOUNT")
    val sc=new SparkContext(conf)
    val first = sc.parallelize(List("dog", "salmon", "salmon", "rat", "elephant"), 3)
    val second = first.keyBy(_.length)//key是字符的长度
    //second.foreach(print(_))
   // val third=first.union(other)
  }
}