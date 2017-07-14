package example

import org.scalatest._

class BucketTest extends FlatSpec with Matchers {
	
    //http://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-buckets.html

  ignore should "create, list, get and delete Bucket" in {
    val s3 = MyS3.create()


    val bucketName = s"rysh-${localName()}-bucket-test"
    val bucket = s3.createBucket(bucketName)
    bucket match {
      case Right(b) => {
        assert(bucket.isRight == true)
        assertResult(s3.getBucket(bucketName).isEmpty, "after create and before delete")(false)
        assert(s3.listBuckets().filter(b => b.getName == bucketName).size == 1)
        s3.deleteBucket(bucketName)
        assertResult(s3.getBucket(bucketName).isEmpty, "after delete")(true)
      }
      case Left(e) => {
        e.printStackTrace()
        println(
          s"""**************************************************
             |**************************************************
             |bucket name : $bucketName
             |${e.getMessage}
             |**************************************************
             |**************************************************""".stripMargin)
        fail()
      }
    }
  }
}
