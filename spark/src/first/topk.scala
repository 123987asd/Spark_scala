package first
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/**
  * Created by hu on 16-11-2.
  */
object topk {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf();
    conf.setMaster("local[2]").setAppName("topk")
    val sc=new SparkContext(conf)
    val data=sc.textFile("/home/hu/data/spark/TOPK");
    val words=data.flatMap(line=>line.split("\t")).map(word=>{var a=1;(word,1)}).reduceByKey(_+_);

    val topword=words.map{case(key,value)=>(value,key)}.sortByKey(false,1)
    print(topword.top(2))
  }

}
