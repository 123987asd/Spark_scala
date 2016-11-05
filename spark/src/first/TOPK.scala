package first
import org.apache.spark.{SparkConf, SparkContext}
/**
  * Created by hu on 16-10-26.
  */
object TOPK {

  def main(args: Array[String]): Unit = {

    val conf=new SparkConf
    conf.setMaster("local[2]").setAppName("TOPK")
    val sc=new SparkContext(conf)
    val data=sc.textFile("/home/hu/data/spark/TOPK");
    val words=data.flatMap(line=>line.split(" ")).map(word=>{var a=1;(word,a)}).reduceByKey(_+_)
    val test=words.values.top(1)
    test.foreach(n=>println(n))
    val count=words.map{case (key,value)=>(value,key)}.sortByKey(false,1) //true 和 false 一个升序 一个降序`
    val TopK=count.top(5) //{ }可以进行格式调整 位置的交换 ()rdd的转换
    //TopK.foreach(n=>println(n))
    //words.foreach(n=>println(n))
  }
}
