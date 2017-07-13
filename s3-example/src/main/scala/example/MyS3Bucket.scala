package example

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.Bucket

/**
  * Created by ryuhei.ishibashi on 2017/07/12.
  */
class MyS3Bucket(s3:MyS3, name: String) {
  val bucket: Either[Exception, Bucket] = s3.createBucket(name)
}

object MyS3Bucket {
  def create(s3:MyS3, bucketName:String):MyS3Bucket = {
    new MyS3Bucket(s3, bucketName)
  }
}