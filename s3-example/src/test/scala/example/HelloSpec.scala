package example

import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
	
    //http://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-buckets.html
  "The Hello object" should "say hello" in {
    createBucket("rysh-test-bucket")
    list()
    deleteBucket("rysh-test-bucket")
  }

  def list(): Unit = {
    println("Your Amazon S3 buckets are:")
    for (b <- MyS3.create().listBuckets) {
      System.out.println("* " + b.getName)
    }
  }

  def createBucket(bucketName:String): Unit = {
    MyS3.create().createBucket(bucketName) match {
      case Right(bucket) => println("success: " + bucket)
      case Left(exception)  => System.err.println(exception)
    }
  }

  def deleteBucket(bucketName : String): Unit = {
    System.out.println(" OK, bucket ready to delete!")
    MyS3.create().deleteBucket(bucketName)
  }

}
