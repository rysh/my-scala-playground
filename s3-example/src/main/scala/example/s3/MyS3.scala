package example.s3

import better.files.File
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.amazonaws.services.s3.model.{PutObjectRequest, Bucket}

import scala.collection.JavaConverters

/**
  * Created by ryuhei.ishibashi on 2017/07/11.
  */
class MyS3(region: Regions) {

  val s3: AmazonS3 = AmazonS3ClientBuilder.standard().withRegion(region).build()

  def upload(bucketName: String, keyName: String, file: File): Any = {
    new MyS3Object(this).upload(bucketName, keyName, file)
  }

  def putObject(req: PutObjectRequest) = {
    s3.putObject(req)
  }



  def listBuckets(): Iterable[Bucket] = {
    JavaConverters.collectionAsScalaIterable(s3.listBuckets())
  }

  def getBucket(name : String) : Option[Bucket] = {
    listBuckets()
      .filter(b => b.getName() == name)
      .collectFirst({case x : Bucket => x})
  }

  def createBucket(name : String): Either[Exception, Bucket] = {
    try {
      getBucket(name) match {
        case Some(bucket) => Right(bucket)
        case None => Right(s3.createBucket(name))
      }
    } catch {
      case e: Exception => Left(e)
    }
  }

  def deleteBucket(name : String): Either[Exception, Unit] = {
    try {
      s3.listObjects(name).getObjectSummaries().forEach(s => s3.deleteObject(name, s.getKey))
      s3.deleteBucket(name)
      Right()
    } catch {
      case e: Exception => Left(e)
    }
  }

}

object MyS3 {
  val region = Regions.US_WEST_1
  def create(): MyS3 = {
    new MyS3(region)
  }
}

class MyS3Bucket(s3:MyS3, name: String) {
  val bucket: Either[Exception, Bucket] = s3.createBucket(name)
}

class MyS3Object(s3: MyS3) {

  def upload(bucketName:String, keyName: String, file: File) = {
    import com.amazonaws.AmazonServiceException

    try {
      val req = new PutObjectRequest(bucketName, keyName, file.toJava)
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