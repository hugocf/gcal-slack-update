package cc.ferreira

import scala.util.{Failure, Success, Try}

package object gcal2slack {
  type Result[T] = Either[Error, T]

  case class Error(message: String)

  implicit class TryWrapper[A](val t: Try[A]) extends AnyVal {
    def toResult: Result[A] = t.toEither.left.map(t => Error(t.getMessage))

    def merge[B](other: Try[B])(f: (A, B) => A): Try[A] =
      (t, other) match {
        case (Success(a), Success(b)) => Success(f(a, b))
        case (Failure(a), _) => Failure(a)
        case (_, Failure(b)) => Failure(b)
      }
  }

  implicit class ListTryWrapper[A](val l: List[Try[A]]) extends AnyVal {
    def sequence: Try[List[A]] =
      l.foldLeft[Try[List[A]]](Success(List.empty))((acc, el) => acc.merge(el)(_ :+ _))
  }
}
