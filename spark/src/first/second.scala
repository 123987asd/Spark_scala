package first
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

object second {

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf();
    conf.setMaster("local[2]").setAppName("first")
    val sc=new SparkContext(conf)
    val data=Array("aa","bb","cc","dd","ee")
    val distData=sc.parallelize(data, 3).map { x => field+x }
    //doStuff(distData)
    distData.foreach { n => print(n) }
    
  }
    val field = "Hello"
  def doStuff(rdd: RDD[String]): RDD[String] = {
    rdd.map(x => field + x) 
  }
}