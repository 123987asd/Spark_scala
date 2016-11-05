package first

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable
import scala.collection.mutable._
/**
  * Created by hu on 16-11-2.
  */

//这个倒排索引 和评估的形成list挺像
object inverted_index {

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf();
    conf.setMaster("local[2]").setAppName("inverted_index")
    val sc=new SparkContext(conf)
    val data=sc.textFile("/home/hu/data/spark/inverted_index")//计算个数的时候使用flatMap 汇聚到一起
    val words=data.map(line=>line.split("\t")).map(item=>{(item(0),item(1))})
    val test=words.flatMap(item=>{
      val word=item._2.split(" ").iterator;
      var map = mutable.Map[String,String]()//形成节点对形式的集合
      val doc=item._1
      while (word.hasNext)
        {
          map+=(word.next()->doc)//一个存储的集合 形成节点对 -> 是scala 的格式 +=也是scala的格式
        }
      map
      })


    //把key 一样的结合起来
   val result=test.reduceByKey(_+" "+_)
     .map(x=>{
      x._1+"\t"+x._2// 为了 以\t分割开
    })

    test.foreach(n=>println(n))



  }
}
