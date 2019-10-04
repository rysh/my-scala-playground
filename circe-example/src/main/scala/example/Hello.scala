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

  val big = Big(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24)
  println(big.asJson)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
case class Big(
  a1:Int,
  a2:Int,
  a3:Int,
  a4:Int,
  a5:Int,
  a6:Int,
  a7:Int,
  a8:Int,
  a9:Int,
  a10:Int,
  a11:Int,
  a12:Int,
  a13:Int,
  a14:Int,
  a15:Int,
  a16:Int,
  a17:Int,
  a18:Int,
  a19:Int,
  a20:Int,
  a21:Int,
  a22:Int,
  a23:Int,
  a24:Int
)
