package example

object Hello extends App {
  // ライブラリを通すとエスケープシーケンスが消費されるので
  val NUL = "\u0000"
  val HT = "\u0009"
  val NEL = "\u0085"
  println(s"[$NUL]")
  println(s"[$HT]")
  println(s"[$NEL]")
  println(s"[$NUL]".replace(NUL,""))
  println(s"[$HT]".replace(HT,""))
  println(s"[$NEL]".replace(NEL,""))

  val alphaPattern = "[\u0000-\u0000]+".r
  println(alphaPattern.replaceAllIn(s"[$NUL]", ""))
}
