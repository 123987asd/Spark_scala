
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object distinctTest {
  //[] 表示 类型 （） 集合 使用的
  def main(args: Array[String]): Unit = {
     val conf=new SparkConf
     conf.setMaster("local[2]").setAppName("distinctTest")
     val sc=new SparkContext(conf)
     //List里面的单独 一个 元素类型  和RDD里面单独的一个元素 类型是一个的
     //下面的List[String] 就是 RDD单独元素的类型 
     //也就是 RDD 和 array 或 list 对应 只是 集合
     val first:RDD[List[String]]=sc.parallelize(List(List("aa_bb","aa_bb","aa_dd"),List("aa_bb","aa_bb","aa_dd")))
     val second=first.distinct();
     second.foreach { n => println(n) }
  
  }
}