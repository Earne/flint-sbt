import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.Context

class flintSer extends StaticAnnotation {
  def macroTransform(annottees: Any*) = macro flintSerMacro.impl
}

object flintSerMacro {
  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    import Flag._
    val result = {
      annottees.map(_.tree).toList match {
        case q"def flintSer($rdd: RDD[Float]): RDD[Float] = { ..$body }" :: Nil =>
          q"""
            def flintSer($rdd: RDD[Float]): RDD[Float] = {
              println("wtf")
              import java.io.DataOutputStream
              $rdd.mapPartitions { iter =>
                val chunk = new FloatChunk(41960)
                val dos = new DataOutputStream(chunk)
                iter.foreach(dos.writeFloat)
                Iterator(chunk)
              }.cache().map(_.max())
            }
          """
      }
    }
    c.Expr[Any](result)
  }
}