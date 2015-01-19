import java.io.DataOutputStream

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SerMaxManual {

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("SerMaxManual").setMaster("local")
    val sc = new SparkContext(sparkConf)
    val slices = 2
    val n = 400 * slices
    val rawData = sc.parallelize((1 to n).map(_.toFloat), slices)
    serMaxManual(rawData)
    sc.stop()
  }

  def serMaxManual(input: RDD[Float]) {
    val cachedRDD = input.mapPartitions { iter =>
      val chunk = new FloatChunk(41960)
      val dos = new DataOutputStream(chunk)
      iter.foreach(dos.writeFloat)
      Iterator(chunk)
    }.cache()
    println("Max value is " + cachedRDD.map(_.max()).max())
  }
}
