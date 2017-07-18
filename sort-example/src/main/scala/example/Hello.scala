package example

object Hello extends App {

  case class User(id:Option[Int], order:Int)

  def sortWithNullFirst(arr: Seq[User]):Seq[User] = {
    arr.sortWith((a, b) => {
      (a.id, b.id) match {
        case (Some(a),Some(b)) => a > b
        case (Some(a) , None) => false // Noneを前に変更
        case _ => true  // 順序は変わらない
      }
    })
  }
}


