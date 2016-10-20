
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkConf
import org.apache.spark.SparkConf


object SimpleApp {

  def main(args: Array[String]): Unit = {
    
    //println("123");
    //var result=sub(multiply(add(1, 2), 3), 3);
    //print(result)
    //val sparkConf = new SparkConf();
    //sparkConf.setMaster("spark://192.168.10.101:7077").setAppName("simpleApp");
    //val sc=new SparkContext("spark://192.168.10.101:7077","SimpleApp")
    val sc=new SparkContext("local[2]","simpleAPP")
    //把对进来的东西看成RDD集合
    val lines=sc.textFile("/home/hu/spark_data/wordcount")
    //lines.foreach { n =>print(n) }
    //print(lines.collect().length)
    val linelength=lines.map(s=>s.length())
    
    val totallength=linelength.reduce((a,b)=>a+b)
    print(totallength)
  }
  def add(a:Int,b:Int):Int=
  {
    return a+b;
  }
  def multiply(a:Int,b:Int):Int=
  {
    return a*b;
  }
  def sub(a:Int,b:Int):Int=
  {
    return a-b;
  }
  
}