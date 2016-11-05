package first
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/**
  * Created by hu on 16-11-2.
  */
object middle {

  //直接排好顺序 取中间的那个数不就行了？ 下面的操作有待理解 有可能是不对的
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf();
    conf.setMaster("local[2]").setAppName("middle");
    val sc=new SparkContext(conf)
    val data=sc.textFile("/home/hu/data/spark/middle").flatMap(line=>line.split(" ")).map(line=>line.toInt);//读取出来的 是字符串
    val mapped=data.map(num=>{(num/4,num)})//分成一组一组的形式 元祖的形式(组号,对应的值)
    val count=mapped.reduceByKey((a,b)=>{a+b}).collect()//每个分组加和
    count.foreach(n=>println(n))
    val sum_count=count.map(data=>{data._2}).sum//所有数的求和
    println(sum_count)
    var temp=0;
    var index=0;
    var mid=sum_count/2
    val length=count.length;
    var foundIt = false
    for(i <-0 until(length)if !foundIt)
      {
        temp=temp+count(i)._2;//spark的rdd没有这种用法 这是scala的数组用法
        if (temp>=mid)//求
          {
            index=i;
            foundIt=true;
          }

      }
    val offset=temp-mid
    println(offset)
    val result=mapped.filter(num=>num._1==index).takeOrdered(offset)
    result.foreach(n=>println(n))
    sc.stop()
    //mapped.foreach(n=>print(n))
  }
}
