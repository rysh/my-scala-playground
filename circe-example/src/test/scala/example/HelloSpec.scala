package example

import org.scalatest._

class HelloSpec extends FunSpec with Matchers {



  describe("The Hello Circe") {
    it("Encoding and decoding") {

    import io.circe._
    import io.circe.generic.auto._
    import io.circe.parser._
    import io.circe.syntax._

    val intJson = List(1, 2, 3).asJson
    val resultOfAs = intJson.as[List[Int]]
    val resultOfDecode = decode[List[Int]]("[1, 2, 3]")
    println(intJson)
    println(resultOfAs)
    println(resultOfDecode)

    resultOfAs shouldEqual resultOfDecode
    }
    it("Semi-automatic derivation") {
      import io.circe._, io.circe.generic.semiauto._

      case class Foo(a: Int, b: String, c: Boolean)

      implicit val fooDecoder: Decoder[Foo] = deriveDecoder[Foo]
      implicit val fooEncoder: Encoder[Foo] = deriveEncoder[Foo]

    }

    it("@JsonCodec") {
      import io.circe.generic.JsonCodec, io.circe.syntax._

      @JsonCodec case class Bar(i: Int, s: String)

      Bar(13, "Qux").asJson
    }
    it("forProductN helper methods") {


      import io.circe.{ Decoder, Encoder }

      case class User(id: Long, firstName: String, lastName: String)

      object UserCodec {
        implicit val decodeUser: Decoder[User] =
          Decoder.forProduct3("id", "first_name", "last_name")(User.apply)

        implicit val encodeUser: Encoder[User] =
          Encoder.forProduct3("id", "first_name", "last_name")(u =>
            (u.id, u.firstName, u.lastName)
          )
      }

    }

    it("Fully automatic derivation") {
      import io.circe.generic.auto._, io.circe.syntax._

      case class Person(name: String)
      case class Greeting(salutation: String, person: Person, exclamationMarks: Int)

      Greeting("Hey", Person("Chris"), 3).asJson
    }

//    it("Custom encoders/decoders") {
//      import io.circe.generic.auto._, io.circe.syntax._
//      import io.circe.{ Decoder, Encoder, HCursor }
//
//
//      class Thing(val foo: String, val bar: Int)
//
//      implicit val encodeFoo: Encoder[Thing] = new Encoder[Thing] {
//        final def apply(a: Thing): Json = Json.obj(
//          ("foo", Json.fromString(a.foo)),
//          ("bar", Json.fromInt(a.bar))
//        )
//      }
//
//      implicit val decodeFoo: Decoder[Thing] = new Decoder[Thing] {
//        final def apply(c: HCursor): Decoder.Result[Thing] =
//          for {
//            foo <- c.downField("foo").as[String]
//            bar <- c.downField("bar").as[Int]
//          } yield {
//            new Thing(foo, bar)
//          }
//      }
//      import cats.syntax.either._
//      import io.circe.{ Decoder, Encoder }
//      import java.time.Instant
//
//      implicit val encodeInstant: Encoder[Instant] = Encoder.encodeString.contramap[Instant](_.toString)
//
//      implicit val decodeInstant: Decoder[Instant] = Decoder.decodeString.emap { str =>
//        Either.catchNonFatal(Instant.parse(str)).leftMap(t => "Instant")
//      }
//    }

    it("Custom key types") {
      import io.circe._, io.circe.syntax._

      case class Foo(value: String)

      implicit val fooKeyEncoder: KeyEncoder[Foo] = new KeyEncoder[Foo] {
        override def apply(foo: Foo): String = foo.value
      }
      val map = Map[Foo, Int](
        Foo("hello") -> 123,
        Foo("world") -> 456
      )

      val json = map.asJson

      implicit val fooKeyDecoder: KeyDecoder[Foo] = new KeyDecoder[Foo] {
        override def apply(key: String): Option[Foo] = Some(Foo(key))
      }

      json.as[Map[Foo, Int]]
    }

    it("Custom key mappings via annotations") {
      import io.circe.generic.extras._, io.circe.syntax._


      import cats.Eq
      import io.circe.{ Decoder, Encoder }


      implicit val config: Configuration = Configuration.default.withSnakeCaseKeys

      @ConfiguredJsonCodec case class User(firstName: String, lastName: String)

      User("Foo", "McBar").asJson
    }
  }
}
