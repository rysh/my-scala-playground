package example

object Hello extends App {
  trait Impl {
    def hello: String
  }
  trait M extends Impl { //インターフェースを継承する
    override def hello = "i am mama"
  }

  trait P extends Impl { //インターフェースを継承する
    override def hello = "i am papa"
  }
  trait C {
    def shout(t: String): String = {
      "%s Gyaaa!!!".format(t)
    }
  }
  class F extends C {
    self: Impl => //自分型アノテーションにインターフェースを設定する
    def greeting: Unit = {
      println(shout(hello))
    }
  }
  val mama = new F with M //helloの実装をココで注入
  mama.greeting

  val papa = new F with P //helloの実装をココで注入
  papa.greeting
  println("greeting")
}
