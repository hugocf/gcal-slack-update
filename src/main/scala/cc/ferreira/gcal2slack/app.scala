package cc.ferreira.gcal2slack

import scala.util.Try

object App extends Main with Services

package object app {
  type Result[T] = Either[Error, T]

  case class Error(message: String)

  implicit class TryWrapper[A](val t: Try[A]) extends AnyVal {
    def toResult: Result[A] = t.toEither.left.map(t => Error(t.getMessage))
  }
}
