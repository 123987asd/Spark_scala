import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object TotalWord {
  
  def main(args: Array[String]): Unit = {
   val conf=new SparkConf()
  conf.setMaster("local[2]").setAppName("TotalWord")
  val sc=new SparkContext(conf)
  val lines=sc.textFile("/home/hu/spark_data/wordcount", 1)
  print(lines.first())
  val words=lines.map { line =>line.split(" ").size }
  val numword=words.reduce((a,b)=>a+b)
  print(numword)  
    
    
  }
}