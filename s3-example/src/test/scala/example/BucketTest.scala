package example

import com.amazonaws.services.s3.model.Bucket
import org.scalatest._

class BucketTest extends FlatSpec with Matchers {
	
    //http://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-buckets.html

  "MyS3Object" should "create, list, get and delete Bucket" in {
    val s3 = MyS3.create()
    val bucketName = "rysh-test-bucket"

    val bucket = s3.createBucket(bucketName)
    assert(bucket.isRight == true)
    assertResult(s3.getBucket(bucketName).isEmpty,"after create and before delete")(false)
    assert(s3.listBuckets().filter(b => b.getName == bucketName).size == 1)

    s3.deleteBucket(bucketName)
    assertResult(s3.getBucket(bucketName).isEmpty, "after delete")(true)
  }


  ignore should "Upload an Object" in {
    ???
  }

  ignore should "List Objects" in {
    ???
  }

  ignore should "Download an Object" in {
    ???
  }

  ignore should "Copy, Move, or Rename Objects" in {
    ???
  }


  ignore should "Delete an Object" in {
    ???
  }
  //Upload an Object
  //List Objects
  //  Download an Object
  //Copy, Move, or Rename Objects
  //Delete an Object
  //Delete Multiple Objects at Once
}
