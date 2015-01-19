import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SerMaxAuto {

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("SerMaxAuto").setMaster("local")
    val sc = new SparkContext(sparkConf)
    val slices = 2
    val n = 400 * slices
    val rawData = sc.parallelize((1 to n).map(_.toFloat), slices)
    serMaxAuto(rawData)
    sc.stop()
  }

  def serMaxAuto(input: RDD[Float]) {
    val cachedRDD = flintSer(input)
    println("Max value is " + cachedRDD.max())
  }

  def flintSer(rdd: RDD[Float]): rdd.type = {
    rdd.cache()
  }
}
