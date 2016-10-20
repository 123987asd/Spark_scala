package first
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

object Third {
  
  
  def main(args: Array[String]): Unit = {
      val conf=new SparkConf()
      conf.setMaster("local[2]").setAppName("Third")
      val sc=new SparkContext(conf)
      //val line="aaa aaa aaa"
      //val bb=line.split(" ")
      //bb.foreach { n => println(n) }
      val data = Array("aa", "bb", "cc", "ee", "ee")
      val distData = sc.parallelize(data)
      
    
  }
  /*
  def split(line:String):Array[String]=
  {
    line.split(" ");
  }
  */
  
 val word="hello"
 def connect(rdd:RDD[String]):RDD[String]=
 {
   rdd.map { words => words+word }
 }
}