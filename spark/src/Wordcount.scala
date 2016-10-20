
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkConf

object Wordcount {
  
  def main(args: Array[String]): Unit = {  
  val conf=new SparkConf()
  conf.setMaster("local[2]").setAppName("Wordcount")
  val sc=new SparkContext(conf)
  val lines=sc.textFile("/home/hu/spark_data/wordcount", 3);
  val words=lines.flatMap { line =>line.split(" ") }
  val word=words.map { word => (word,1) }
  val wordcount=word.reduceByKey(_+_)
  wordcount.collect()
  wordcount.foreach(n=>print(n))
}
}