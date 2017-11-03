package example.s3

import better.files.File
import example.localName
import org.scalatest.{BeforeAndAfterAll, FunSuite}

/**
  * Created by ryuhei.ishibashi on 2017/07/12.
  */
class MyS3ObjectTest extends FunSuite with BeforeAndAfterAll {

  val s3 = MyS3.create()
  val bucketName = s"rysh-${localName()}-my-s3-object-test"
  val file = File("my-s3-object-test").createIfNotExists()

  override def beforeAll() {
    val bucket = s3.createBucket(bucketName)
  }

  override def afterAll() {
    file.delete()
    s3.deleteBucket(bucketName)
  }

  test("Upload an Object") {
    s3.upload(bucketName, "my-s3-object-test", file)
  }

  ignore("List Objects") {
    ???
  }

  ignore("Download an Object") {
    ???
  }

  ignore("Copy, Move, or Rename Objects") {
    ???
  }

  ignore("Delete an Object") {
    ???
  }

  ignore("Delete Multiple Objects at Once") {
    ???
  }
}
