package example

import java.util.concurrent.atomic.AtomicInteger

import org.scalatest._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class HelloSpec extends FlatSpec with Matchers {

  val counter = new AtomicInteger()
  "The Hello object" should "say hello" in {

    def createFuture(sleepingTime:Int) = {
      Future {
        val no = counter.addAndGet(1)
        println(s"no.$no : start sleeping $sleepingTime")
        Thread.sleep(sleepingTime)
        println(s"no.$no : end sleeping $sleepingTime")
        no
      }
    }

    val f: Future[Int] = createFuture(100)
    val f2: Future[Int] = createFuture(50)

    val hoge = for {
      res1 <- f
      res2 <- f2
      res3 <- createFuture(100)
      res4 <- createFuture(50)
      f5 = createFuture(100)
      f6 = createFuture(50)
      res5 <- f5
      res6 <- f6
    } yield s"${res1}, ${res2}, ${res3}, ${res4}, ${res5}, ${res6}"
    println(Await.result(hoge, Duration.Inf))
  }
}
