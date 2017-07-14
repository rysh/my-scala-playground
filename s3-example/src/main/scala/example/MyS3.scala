package example

import java.util

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.{AmazonS3, AmazonS3Client, AmazonS3ClientBuilder}
import com.amazonaws.services.s3.model.{AmazonS3Exception, Bucket, ObjectListing, PutObjectRequest}

import scala.collection.JavaConverters

/**
  * Created by ryuhei.ishibashi on 2017/07/11.
  */
class MyS3(region: Regions) {
  def putObject(req: PutObjectRequest) = {
    s3.putObject(req)
  }

  val s3: AmazonS3 = withCredentials

  private def defaultClient = {
    AmazonS3ClientBuilder.defaultClient()
  }
  private def standardWithRegion = {
    AmazonS3ClientBuilder.standard().withRegion(region).withPathStyleAccessEnabled(true).build()
  }

  private def withCredentials = {
    val credentialsProvider = new ProfileCredentialsProvider
    val builder = AmazonS3ClientBuilder.standard()
    builder.withRegion(region).build()
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