import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.Context

class flintSer extends StaticAnnotation {
  def macroTransform(annottees: Any*) = macro flintSerMacro.impl
}

object flintSerMacro {
  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    val result = {
      annottees.map(_.tree).toList match {
        case q"def flintSer(rdd: RDD[Float]): rdd.type = { ..$body }" :: Nil =>
          q"""
            def flintSer(rdd: RDD[Float]): rdd.type = {
              println("wtf")
              ..$body
            }
          """
      }
    }
    c.Expr[Any](result)
  }
}