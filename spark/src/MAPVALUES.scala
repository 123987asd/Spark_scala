import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/**
  * Created by hu on 16-10-15.
  */
object MAPVALUES {

  def main(args: Array[String]): Unit = {

    val conf=new SparkConf;
    conf.setMaster("local[2]").setAppName("")
    val sc=new SparkContext(conf)
    val test=sc.parallelize(Array(("aa",1),("bb",2)))
    val test1=test.mapValues(n=>n+1)
    test1.foreach(n=>println(n))
  }
}
