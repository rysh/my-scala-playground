package example

import org.scalatest.{BeforeAndAfterAll, FunSuite}

import scala.concurrent.{ExecutionContext, Future, Promise}

class FuturesIntroductionTest extends FunSuite with BeforeAndAfterAll {
  implicit var ec = ExecutionContext.global

  override def afterAll: Unit = {
    ec = null
  }

  test("Execution Contexts") {

    // Do stuff with your brand new shiny ExecutionContext
    val f: Promise[String] = Promise.successful("foo")


    // Then shut your ExecutionContext down at some
    // appropriate place in your program/application
  }

  test("Use Directly") {
    val future = Future {
      "Hello" + "World"
    }
    future foreach println
  }

  test("Functional Futures") {

    def future_is_a_Monad():Unit =  {
      val f1 = Future {
        "Hello" + "World"
      }
      val f2 = f1 map { x =>
        x.length
      }
      f2 foreach println
    }

    def future_is_a_Monad_2():Unit =  {
      val f1 = Future {
        "Hello" + "World"
      }
      val f2 = Future.successful(3)
      val f3 = f1 map { x =>
        f2 map { y =>
          x.length * y
        }
      }
      f3 foreach println
    }


    def future_is_a_Monad_3():Unit =  {
      val f1 = Future {
        "Hello" + "World"
      }
      val f2 = Future.successful(3)
      val f3 = f1 flatMap { x =>
        f2 map { y =>
          x.length * y
        }
      }
      f3 foreach println
    }


    def future_is_a_Monad_4():Unit =  {
      val future1 = Future.successful(4)
      val future2 = future1.filter(_ % 2 == 0)

      future2 foreach println

      val failedFilter = future1.filter(_ % 2 == 1).recover {
        // When filter fails, it will have a java.util.NoSuchElementException
        case m: NoSuchElementException => 0
      }

      failedFilter foreach println
    }
    future_is_a_Monad()
    future_is_a_Monad_2()
    future_is_a_Monad_3()
    future_is_a_Monad_4()

  }


  test("For Comprehensions") {
    val f = for {
      a <- Future(10 / 2) // 10 / 2 = 5
      b <- Future(a + 1) //  5 + 1 = 6
      c <- Future(a - 1) //  5 - 1 = 4
      if c > 3 // Future.filter
    } yield b * c //  6 * 4 = 24

    // Note that the execution of futures a, b, and c
    // are not done in parallel.

    f foreach println
  }
}
