package first
import org.apache.spark
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/**
  * Created by hu on 16-11-2.
  */
object Middle {
  //这是对的
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf();
    conf.setMaster("local[2]").setAppName("Middle")
    val sc=new SparkContext(conf)
    val data=sc.textFile("/home/hu/data/spark/middle").flatMap(line=>line.split(" ")).map(word=>word.toInt);
    //sortbyKey 是对于 key value类型的数组 才可以使用 对于只有key的数据是不可以使用的
    val mapped=data.map(word=>(word/3,word))//把数组分组 分组的的格式是本身/3的结果 //形成ley value类型的数据
    val mapped1=mapped.map({case (key,value)=>(value,key)})
    mapped1.foreach(n=>println(n))
    val sort=mapped1.sortByKey()//把key 进行排序
    var length=sort.count()
    var mid=length/2
    val result=mapped1.filter(word=>word._1==mid)
    result.foreach(n=>println(n))
    val sum=mapped.map(data=>data._2).sum()//计算每个分组的 总和
    println(sum)

  }
}
