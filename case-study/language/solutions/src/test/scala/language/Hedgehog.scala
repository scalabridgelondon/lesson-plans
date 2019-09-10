package language

import cats._
import hedgehog.Gen

/**
 * Cats instances for hedgehog
 */
object Hedgehog {
  implicit object GenMonad extends Monad[Gen] {
    def flatMap[A,B](fa: Gen[A])(f: A => Gen[B]): Gen[B] =
      fa.flatMap(f)

    def pure[A](x: A): Gen[A] =
      Gen.constant(x)

    def tailRecM[A,B](a: A)(f: A => Gen[Either[A,B]]): Gen[B] =
      f(a).flatMap{ e =>
        e match {
          case Left(a) => tailRecM(a)(f)
          case Right(b) => Gen.constant(b)
        }
      }
  }
}
