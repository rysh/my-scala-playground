package example

import org.scalatest._

class WriteSpec extends FlatSpec with Matchers {

  // https://github.com/pathikrit/better-files
  it should "write line" in {
    Hello.greeting shouldEqual "hello"

    import better.files._
    import java.io.{File => JFile}
    val file: File = File("hoge.txt").createIfNotExists()

    import better.files.Dsl.SymbolicOperations
    file << "world"
    assert(file.contentAsString.contains("world"))

    file.delete()
  }
}
