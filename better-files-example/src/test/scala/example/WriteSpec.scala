package example

import org.scalatest._

class WriteSpec extends FlatSpec with Matchers with BeforeAndAfterAll {


  import better.files._
  val file: File = File("WriteSpec.txt").createIfNotExists()
  // https://github.com/pathikrit/better-files
  it should "write line" in {

    import better.files.Dsl.SymbolicOperations
    file << "world"
    assert(file.contentAsString.contains("world"))


  }

  override def afterAll(): Unit = {
    file.delete()
  }
}
