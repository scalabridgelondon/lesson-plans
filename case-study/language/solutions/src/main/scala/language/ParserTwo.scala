package language

object ParserTwo {

  sealed trait Expr

  object Expr {
    case class Lit(value: Int) extends Expr
    case class Add(left: Expr, right: Expr) extends Expr
    case class Mul(left: Expr, right: Expr) extends Expr
  }

  sealed trait Result[+A] {
    def map[B](f: A => B): Result[B] = this match {
      case Result.Success(a, rem) => Result.Success(f(a), rem)
      case fail: Result.Failure => fail
    }
  }

  object Result {
    case class Success[A](value: A, remaining: String) extends Result[A]
    case class Failure(remaining: String) extends Result[Nothing]
  }

  sealed trait Parser[A] {

    def parse(text: String): Result[A]

    // Convenience functions to construct ADT
    def map[B](f: A => B): Parser[B] = Parser.Map(this, f)
    def |(parser: => Parser[A]): Parser[A] = Parser.Alternative(() => this, () => parser)
    def repeat: Parser[(A, List[A])] = Parser.Repeat(this)
    def ~[B](parser: => Parser[B]): Parser[(A, B)] = Parser.Zip(() => this, () => parser)

    // Derived methods
    def optional: Parser[Option[A]] = this.map(Option(_)) | Parser.Empty.map(_ => None)
    def <*[B](parser: => Parser[B]): Parser[A] = (this ~ parser).map(_._1)
    def *>[B](parser: => Parser[B]): Parser[B] = (this ~ parser).map(_._2)
    def void: Parser[Unit] = this.map(_ => ())
  }

  // Our cases are defined here. Ideally we would match inside the sealed trait
  // but the GADT problem stops this.
  object Parser {

    case object Empty extends Parser[Unit] {
      def parse(text: String): Result[Unit] = Result.Success((), text)
    }

    case class Character(value: Char) extends Parser[Char] {
      def parse(text: String): Result[Char] = text.headOption match {
        case Some(c) if c == value => Result.Success(c, text.tail)
        case _ => Result.Failure(text)
      }
    }

    // Corresponds to SemigroupK type class
    case class Alternative[A](left: () => Parser[A], right: () => Parser[A]) extends Parser[A] {
      def parse(text: String): Result[A] = {
        left().parse(text) match {
          case result: Result.Success[A] => result
          case _: Result.Failure => right().parse(text)
        }
      }
    }

    type NonEmptyList[+A] = (A, List[A])

    // Use a free encoding so we can use an arbitary Semigroup combine later
    case class Repeat[A](parser: Parser[A]) extends Parser[NonEmptyList[A]] {

      def parse(text: String): Result[NonEmptyList[A]] = {
        parser.parse(text) match {
          case Result.Success(a, remaining) =>
            repeat(remaining).map { as => (a, as)}
          case f: Result.Failure => f
        }
      }

      private def repeat(text: String): Result[List[A]] = {
        parser.parse(text) match {
          case Result.Success(a, remaining) =>
            repeat(remaining).map { as => a :: as }
          case _: Result.Failure => Result.Success(Nil, text)
        }
      }
    }

    // Corresponds to Semigroupal type class
    //
    // Semigroupal makes this a GADT, instead of a standard ADT, as it has a
    // concrete type on the RHS (the `extends Parser[(A,B)]` has a `Tuple2` in
    // it, though the syntax does not make that explicit.)
    // We need to use a thunk because we can "tie the knot" during creation of parsers
    case class Zip[A, B](left: () => Parser[A], right: () => Parser[B]) extends Parser[(A, B)] {

      def parse(text: String): Result[(A, B)] = {
        left().parse(text) match {
          case Result.Success(a, remaining) =>
            right().parse(remaining).map(b => (a, b))
          case f: Result.Failure => f
        }
      }
    }

    // Corresponds to Functor type class
    case class Map[A, B](parser: Parser[A], f: A => B) extends Parser[B] {
      def parse(text: String): Result[B] = parser.parse(text).map(f)
    }

    case object End extends Parser[Unit] {
      def parse(text: String): Result[Unit] = if(text.isEmpty) {
        Result.Success((), "")
      } else {
        Result.Failure(text)
      }
    }

    def char(c: Char): Parser[Char] =
      Parser.Character(c)
  }

  object ParserExample {

    val digits: Parser[Int] = (0 to 9)
      .map(_.toString.head)
      .map(Parser.char)
      .reduce(_ | _)
      .repeat
      .map {
        case (h, ts) => ts.foldLeft(h.toString)(_ + _.toString)
      }
      .map(_.toInt)

    val literalExpr: Parser[Expr] = digits.map(Expr.Lit(_))

    val plusSymbol: Parser[Unit] = Parser.char('+').void
    val mulSymbol: Parser[Unit] = Parser.char('*').void

    def plusExpr: Parser[Expr] = (literalExpr ~ plusSymbol ~ expr).map {
      case ((x, _), y) => Expr.Add(x, y)
    }

    val multiply: Parser[Expr] = ((literalExpr <* mulSymbol).repeat ~ literalExpr).map {
      case ((a, Nil), b) => Expr.Mul(a, b)
      case ((a, aa :: as), b) =>
        Expr.Mul(as.foldLeft(Expr.Mul(a, aa))(Expr.Mul(_, _)), b)
    }

    // The reason why this is defined like, rather than a mirror of `plusExpr` is to
    // deal with operator precedence.
    def multiplyExpr: Parser[Expr] = (multiply ~ (plusSymbol *> expr).optional).map {
      case (x, Some(y)) => Expr.Add(x, y)
      case (x, None) => x
    }

    def expr: Parser[Expr] = (plusExpr | multiplyExpr | literalExpr) <* Parser.End

  }

}
