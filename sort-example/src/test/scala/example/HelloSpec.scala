package example

import example.Hello.User
import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {


  it should "sort with Null First" in {
    val arr: Seq[User] = List(User(Some(1), 3), User(None, 1), User(Some(2), 2))

    assert(Hello.sortWithNullFirst(arr) ===  arr.sortBy(_.order))
  }
}
