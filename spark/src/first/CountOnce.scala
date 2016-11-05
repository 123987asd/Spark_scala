package first

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by hu on 16-11-3.
  */
object CountOnce {

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf
    conf.setMaster("local[2]").setAppName("CountOnce")
    val sc=new SparkContext(conf)
    val data=sc.textFile("/home/hu/data/spark/CountOnce");
    val result = data.mapPartitions( iter => {
      var tmp = iter.next().toInt
      while (iter.hasNext) {
        tmp ^= iter.next().toInt
      }
      Seq((1, tmp)).iterator
    }).reduceByKey(_ ^ _).collect
    println(result(0))
  }
}
