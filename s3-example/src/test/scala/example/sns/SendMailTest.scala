package example.sns

import org.scalatest.{BeforeAndAfterAll, FunSuite}
import com.amazonaws.services.sns.{AmazonSNSClient, AmazonSNSClientBuilder, AmazonSNS}
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.amazonaws.services.sns.model.CreateTopicRequest
import com.amazonaws.services.sns.model.CreateTopicResult
import com.amazonaws.services.sns.model.SubscribeRequest
import com.amazonaws.services.sns.model.PublishRequest
import com.amazonaws.services.sns.model.PublishResult
import com.amazonaws.services.sns.model.DeleteTopicRequest

class SendMailTest extends FunSuite with BeforeAndAfterAll {


  override def beforeAll(): Unit = {
  }

  override def afterAll() {
  }

  test("sendmail") {
    val snsClient: AmazonSNS = AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2).build()

    val topicArn = "arn:aws:sns:us-east-2:855331286585:scalatrader-notifications"
    val msg = "My text published to SNS topic with email endpoint"
    val publishRequest = new PublishRequest(topicArn, msg)

    val publishResult = snsClient.publish(publishRequest)
    System.out.println("MessageId - " + publishResult.getMessageId)

  }

}
