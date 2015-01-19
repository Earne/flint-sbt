import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SerMax {

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("SerMax").setMaster("local")
    val sc = new SparkContext(sparkConf)
    val slices = 2
    val n = 400 * slices
    val rawData = sc.parallelize((1 to n).map(_.toFloat), slices)
    serMax(rawData)
    sc.stop()
  }

  def serMax(input: RDD[Float]) {
    val cachedRDD = input.cache()
    println("Max value is " + cachedRDD.max())
  }
}
