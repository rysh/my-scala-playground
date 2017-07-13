package example

import org.scalatest.{BeforeAndAfterEach, FunSuite}

/**
  * Created by ryuhei.ishibashi on 2017/07/12.
  */
class MyS3ObjectTest extends FunSuite with BeforeAndAfterEach {

  override def beforeEach() {

  }

  override def afterEach() {

  }

  ignore("Upload an Object") {

    MyS3Object.create().upload()
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
