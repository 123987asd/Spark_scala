import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import java.lang.Math
//var 可以被多次赋值 val 一单被赋值 就不能变化
object WordCount {
  
  def main(args: Array[String]): Unit = {  
 
  val conf=new SparkConf()
  conf.setMaster("local[2]").setAppName("Maxlength")
  val sc=new SparkContext(conf)
  val lines=sc.textFile("/home/hu/spark_data/wordcount", 1)
  val words=lines.flatMap(split)
  val singeword=words.map { word =>(word,1) }
  val wordcount=singeword.reduceByKey(_+_)
  wordcount.foreach(n=>print(n))
  }
  //灵活使用 rdd之间的转换 类型 和 格式的 转换  不用拘束 于 RDD[String] 和 Array[String]
  //rdd 是能对map 函数 和reduce 函数调用的 
  //split rdd 没有 只有 返回Array 才有 返回值问题 Array[String] 和 RDD[String]不同
  def split(line:String):Array[String]=
  {
      line.split(" ")
  }
 
}