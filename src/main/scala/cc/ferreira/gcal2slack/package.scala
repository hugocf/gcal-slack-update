package cc.ferreira

import scala.util.Try

package object gcal2slack {
  type Result[T] = Either[Error, T]

  case class Error(message: String)

  implicit class TryWrapper[A](val t: Try[A]) extends AnyVal {
    def toResult: Result[A] = t.toEither.left.map(t => Error(t.getMessage))
  }
}
