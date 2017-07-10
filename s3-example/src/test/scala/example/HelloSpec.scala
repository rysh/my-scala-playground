package example

import org.scalatest._
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.model.Bucket

class HelloSpec extends FlatSpec with Matchers {
	
    //http://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-buckets.html
  "The Hello object" should "say hello" in {
    createBucket("rysh-test-bucket")
    list()
    deleteBucket("rysh-test-bucket")
  }

  def list(): Unit = {
    import com.amazonaws.services.s3.AmazonS3ClientBuilder
    val s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_1).build()

    val buckets = s3.listBuckets
    System.out.println("Your Amazon S3 buckets are:")
    import scala.collection.JavaConversions._
    for (b <- buckets) {
      System.out.println("* " + b.getName)
    }
  }

  def createBucket(bucketName:String): Unit = {
    import com.amazonaws.services.s3.AmazonS3ClientBuilder
    import com.amazonaws.services.s3.model.AmazonS3Exception
    val s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_1).build()
    var b : Bucket = null
    try
      b = s3.createBucket(bucketName)
    catch {
      case e: AmazonS3Exception =>
        System.err.println(e.getErrorMessage)
    }

  }
  def deleteBucket(bucketName : String): Unit = {
    import com.amazonaws.services.s3.AmazonS3
    import com.amazonaws.services.s3.AmazonS3ClientBuilder
    val s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_1).build()
    System.out.println(" OK, bucket ready to delete!")
    s3.deleteBucket(bucketName)
  }


  def getBucket(bucket_name: String) : Bucket = {
    import com.amazonaws.services.s3.AmazonS3
    import com.amazonaws.services.s3.AmazonS3ClientBuilder
    val s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_1).build()
    var named_bucket : Bucket = null
    val buckets = s3.listBuckets
    import scala.collection.JavaConversions._
    for (b <- buckets) {
      if (b.getName.equals(bucket_name)) named_bucket = b
    }
    named_bucket
  }

  def deleteBucket(): Unit = {

  }
}
