package first
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkConf

object first {
  
  def main(args: Array[String]): Unit = {
    
    val conf=new SparkConf();
    conf.setMaster("local[2]").setAppName("first")
    val sc=new SparkContext(conf)
    val data=Array(1,2,3,4,5)
    val distData=sc.parallelize(data, 3)
    val add=distData.reduce((a,b)=>a+b)
    print(add)
    val collect=distData.reduce(first.add)
    print(collect)
  }
  def add(a:Int,b:Int):Int=
  {
    return a+b
  }
}