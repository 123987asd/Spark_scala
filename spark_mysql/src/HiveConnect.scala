/**
  * Created by hu on 16-10-21.
  */
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkContext,SparkConf}
object HiveConnect {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
    conf.setMaster("local[2]").setAppName("HiveConnect")
    val sc=new SparkContext(conf)
    val hiveContext = new HiveContext(sc)
    val url="jdbc:hive://192.168.10.101:10008/default"
    val prop=new java.util.Properties()
    prop.setProperty("user","")
    prop.setProperty("password","")
    val result=hiveContext.read.jdbc(url,"t1",prop)
    hiveContext.sql("show tables").collect().foreach(println)
    println(result.count())
  }

}
