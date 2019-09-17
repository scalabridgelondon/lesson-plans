package language

object ParserOne {

  sealed trait Result[+A] {
    def map[B](f: A => B): Result[B] = this match {
      case Success(a, remaining) => Success(f(a), remaining)
      case fail @ Failure(_) => fail
    }
  }
  final case class Success[A](a: A, remaining: String) extends Result[A]
  final case class Failure(remaining: String) extends Result[Nothing]

  sealed trait Parser[A] {
    import Parser._

    def parse(text: String): Result[A] =
      this match {
        case Text(v) =>
          if(text.startsWith(v)) Success(v, text.substring(v.length))
          else Failure(text)

        case End =>
          if(text.isEmpty) Success((), text)
          else Failure(text)

        case Map(s, f) => s.parse(text).map(f)

        case Zip(l, r) =>
          l.parse match {
            case Success(a, r) => r.parse(r).map(b => (a, b))
            case Failure(r) => Failure(r)
          }

        case Alternative(l, r) =>
          l.parse(text) match {
            case Success(a, r) => Success(a, r)
            case Failure(_) => r.parse(text)
          }

        case Repeat(p, c) =>
          p.parse(text) match {
            case Success(a1, r1) =>
              p.parse(r1) match {
                case Success(a2, r2) => Success(c(a1, a2), r2)
                case Failure(_) => Success(a1, r1)
              }
            case Failure(r) => Failure(r)
          }
      }

    def map[B](f: A => B): Parser[B] =
      Map(this, f)

    def zip[B](that: Parser[B]): Parser[(A,B)] =
      Zip(this, that)

    def or(that: Parser[A]): Parser[A] =
      Alternative(this, that)

    def repeat(combine: (A, A) => A): Parser[A] =
      Repeat(this, combine)

    // Derived methods ------------------------------------------------

    def *>[B](that: Parser[B]): Parser[B] =
      this.zip(that).map{ case (_, b) => b }

    def <*[B](that: Parser[B]): Parser[A] =
      this.zip(that).map{ case (a, _) => a }

    def void: Parser[Unit] =
      this.map(_ => ())
  }

  object Parser {
    final case class Text(value: String) extends Parser[String]
    final case object End extends Parser[Unit]
    // Corresponds to Functor type class
    final case class Map[A,B](source: Parser[A], f: A => B) extends Parser[B]
    // Corresponds to Semigroupal type class
    //
    // Semigroupal makes this a GADT, instead of a standard ADT, as it has a
    // concrete type on the RHS (the `extends Parser[(A,B)]` has a `Tuple2` in
    // it, though the syntax does not make that explicit.)
    final case class Zip[A,B](left: Parser[A], right: Parser[B]) extends Parser[(A,B)]
    // Corresponds to SemigroupK type class
    final case class Alternative[A](left: Parser[A], right: Parser[A]) extends Parser[A]
    // The combine function corresponds to there being a Monoid instance for A
    final case class Repeat[A](parser: Parser[A], combine: (A, A) => A) extends Parser[A]

    def text(value: String): Parser[String] =
      Text(value)

    val end: Parser[Unit] = End

    val digits: Parser[Int] = (0 to 9)
      .map(_.toString)
      .map(Parser.text)
      .reduce((x, y) => x.or(y))
      .repeat(_ ++ _)
      .map(_.toInt)

    // Expr is an algebraic data type
    sealed trait Expr extends Product with Serializable
    final case class Literal(value: Int) extends Expr
    final case class Add(l: Expr, r: Expr) extends Expr
    final case class Multiply(l: Expr, r: Expr) extends Expr

    val plus: Parser[Unit] = text("+").void
    val multiply: Parser[Unit] = text("*").void

    val literalExpr: Parser[Expr] = digits.map(Literal(_))
    def expr: Parser[Expr] =
      (plusExpr.or(multiplyExpr).or(literalExpr)) <* end
    def plusExpr: Parser[Expr] =
      (literalExpr <* plus).zip(expr).map{ case (a, b) => Add(a, b) }
    def multiplyExpr: Parser[Expr] =
      (literalExpr <* multiply).zip(expr).map{ case (a, b) => Multiply(a, b) }
  }
}
