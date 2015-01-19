import java.io.ByteArrayOutputStream

import org.apache.hadoop.io.WritableComparator

/**
 * Created by earne on 1/19/15.
 */
class FloatChunk(size: Int = 4196) extends ByteArrayOutputStream(size) {
  def max(): Float = {
    var maxValue = 0.0f
    var currentValue = 0.0f
    var offset = 0
    while (offset <= count) {
      currentValue = WritableComparator.readFloat(buf, offset)
      if (currentValue > maxValue) {
        maxValue = currentValue
      }
      offset += 4
    }
    maxValue
  }
}