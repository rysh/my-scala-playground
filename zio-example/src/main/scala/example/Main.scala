package example

import java.io.IOException

import zio.{DefaultRuntime, ZIO}
import zio.console._

import scala.concurrent.Future

object Main extends App with DefaultRuntime{
  val zioFromFuture = ZIO.fromFuture(_ => Future.successful("hoge")).mapError(_ => new IOException())

  val myAppLogic =
    for {
      futureHoge <- zioFromFuture
      _    <- putStrLn("Hello! What is your name?")
      name <- getStrLn
      _    <- putStrLn(s"Hello, ${name}/${futureHoge}, welcome to ZIO!")
      futureHoge2 <- zioFromFuture
    } yield ()

  unsafeRun(myAppLogic)

  private val customEnvironment = new Console {
    override val console: Console.Service[Any] = new Console.Service[Any] {
      override def putStr(line: String): ZIO[Any, Nothing, Unit] = ???

      override def putStrLn(line: String): ZIO[Any, Nothing, Unit] = {
        println(line)
        ZIO.succeed(line)
      }

      override val getStrLn: ZIO[Any, IOException, String] = ZIO.succeed("hoge")
    }
  }
  unsafeRun(myAppLogic.provide(customEnvironment))


  val zioFromFunction: ZIO[Int, Nothing, Int] = ZIO.fromFunction((i: Int) => {
    i * i
  })

  unsafeRun(zioFromFunction.provide(1))
  println("hoge")
}