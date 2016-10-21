/**
  * Created by hu on 16-10-20.
  */

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
object mysqlConnect {

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf
    conf.setAppName("mysql_Connect").setMaster("local[2]")
    val sc=new SparkContext(conf)
    val sqlContext=new SQLContext(sc)
    val url="jdbc:mysql://localhost:3306/test"
    val prop=new java.util.Properties()
    prop.setProperty("user","root")
    prop.setProperty("password","hu123")
    val result=sqlContext.read.jdbc(url,"student",prop)
    println(result.count())
  }
}
