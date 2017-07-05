package example


object Hello extends Greeting with App {
import io.circe.generic.JsonCodec, io.circe.syntax._

@JsonCodec case class Person(id: Long, hogeName: String, age: Int)


  println(greeting)

  //Encoding and decoding
  import io.circe._
  import io.circe.generic.auto._
  import io.circe.parser._
  import io.circe.syntax._
  
  val intJson = List(1, 2, 3).asJson
  println(intJson)
  println(intJson.asJson.as[List[Int]])
  println(decode[List[Int]]("[1, 2, 3]"))	


  // Semi-automatic derivation
  import io.circe._, io.circe.generic.semiauto._

  case class Foo(a: Int, b: String, c: Boolean)

  implicit val fooDecoder: Decoder[Foo] = deriveDecoder[Foo]
  implicit val fooEncoder: Encoder[Foo] = deriveEncoder[Foo]
  

  val person = Person(123l, "Taro", 15)

  // Encode (case class -> JSON)
  val encodeData: String = person.asJson.noSpaces
  println(encodeData)
  // {"id":123,"name":"Taro","age":15}

  // Decode (JSON -> case class)
  val decodedData: Either[Error, Person] = decode[Person](encodeData)
  println(decodedData)
  // Right(Person(Id(123),Name(Taro),Age(15)))
}

trait Greeting {
  lazy val greeting: String = "hello"
}
