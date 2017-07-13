package example

import better.files.File
import com.amazonaws.services.s3.model.{ObjectMetadata, PutObjectRequest}


/**
  * Created by ryuhei.ishibashi on 2017/07/12.
  */
class MyS3Object(s3:MyS3) {



  def upload() = {
    import com.amazonaws.services.s3.AmazonS3ClientBuilder
    import com.amazonaws.AmazonServiceException
    val bucketName = ""
    val keyName = ""

    val s3 = AmazonS3ClientBuilder.defaultClient
    try {
      val file = File("MyS3Object-test")
      val req = new PutObjectRequest(bucketName, keyName ,file.toJava)
      s3.putObject(req)
    } catch {
      case e: AmazonServiceException =>
        System.err.println(e.getErrorMessage)
        System.exit(1)
    }

  }
  def list() = ???
  def download() = ???
  def delete() = ???

}

object MyS3Object {


  def create(): MyS3Object = {
    new MyS3Object(MyS3.create())
  }
}