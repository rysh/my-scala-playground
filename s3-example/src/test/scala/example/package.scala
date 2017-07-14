import java.time.{ZoneId, ZonedDateTime}
import java.time.format.DateTimeFormatter

/**
  * Created by ryuhei.ishibashi on 2017/07/13.
  */
package object example {

  def localName():String = {
    ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("mm-ss"))
  }
}
