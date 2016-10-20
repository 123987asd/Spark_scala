package graphx

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.graphx.Edge
import org.apache.spark.graphx.VertexId
import org.apache.spark.graphx.Graph
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx.util.GraphGenerators
import org.apache.spark.graphx.{Graph, VertexRDD}
import org.apache.spark.graphx.{Graph, EdgeRDD}
object first {
  
  def main(args: Array[String]): Unit = {
    
        val conf=new SparkConf
        conf.setMaster("local[2]").setAppName("first")
        val sc=new SparkContext(conf)
        
        val users:RDD[(VertexId,String)]=sc.parallelize(Array((3L,""),(7L,""),(5L,""),(2L,"")))
        
        val relations:RDD[Edge[Int]]=sc.parallelize(Array(Edge(3L,7L,5), Edge(5L, 3L, 7),
                       Edge(2L, 5L, 3), Edge(5L, 7L, 6)))
        //这里的uses 和 relations的RDD格式有要求 
        val graph=Graph(users,relations)
        val nums:RDD[Int]=graph.triplets.map(edge =>
        edge.srcAttr.size)
        nums.foreach { n => print(n) }
        
        //graph.vertices.foreach(print(_))
        //graph.edges.foreach { n =>print(n) }
        // : 冒号后面是返回类型
        val neighbor:VertexRDD[Int]=graph.aggregateMessages[Int](
         
          edge=>
          {
          edge.sendToDst(edge.attr)
          },
          (a,b)=>a+b
          )
         neighbor.collect.foreach(print(_))//结果(3,7)(7,11)(5,3)
        //计算多有目的节点的权重 所以可以用别的方法计算邻里公灰度 把权重都设为1 分别计算目的节点和源节点的权重 就是出入度
        //解释 目的节点 只有3 7 5 所以第一个元素是3 7 5
  }
}