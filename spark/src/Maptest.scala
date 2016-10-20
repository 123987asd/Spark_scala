
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
object Maptest {
  
  def main(args: Array[String]): Unit = {
     val conf=new SparkConf
     conf.setMaster("local[2]").setAppName("mapTest")
     val sc=new SparkContext(conf)
     val test=sc.parallelize(Array("aaa,aaa,bbb"))
     //只要 是能操作的类型 =>后面 是可以调前面的
     val value=test.map { a =>a.length() }
  }
}