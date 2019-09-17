package language

object ParserOne {

  sealed trait Result[+A] {
    def map[B](f: A => B): Result[B] = this match {
      case Success(a, remaining) => Success(f(a), remaining)
      case fail @ Failure(_) => fail
    }
  }

  case class Success[A](a: A, remaining: String) extends Result[A]
  case class Failure(remaining: String) extends Result[Nothing]

  final class Parser[A](f: String => Result[A]) {
    def parse(text: String): Result[A] = f(text)
    def map[B](f: A => B): Parser[B] = {
      new Parser(remaining => this.f(remaining).map(f))
    }

    def combine[B, C](parser: => Parser[B])(f: (A, B) => C): Parser[C] = {
      new Parser(remaining => this.parse(remaining) match {
        case Success(a, remaining) => parser.parse(remaining).map(b => f(a, b))
        case fail @ Failure(_) => fail
      })
    }

    def combine[B, C, D](parserB: => Parser[B], parserC: => Parser[C])(f: (A, B, C) => D): Parser[D] = {
      new Parser(remaining => this.parse(remaining) match {
        case Success(a, remaining) => parserB.parse(remaining) match {
          case Success(b, remaining) => parserC.parse(remaining).map(c => f(a, b, c))
          case fail @ Failure(_) => fail
        }
        case fail @ Failure(_) => fail
      })
    }

    def *>[B](parser: Parser[B]): Parser[B] = {
      this.combine(parser)((_, b) => b)
    }


    def <*[B](parser: Parser[B]): Parser[A] = {
      this.combine(parser)((a, _) => a)
    }

    def or(parser: Parser[A]): Parser[A] = {
      new Parser(remaining => this.parse(remaining) match {
        case success @ Success(_, _) => success
        case Failure(_) => parser.parse(remaining)
      })
    }

    def optional: Parser[Option[A]] = {
      new Parser(remaining => this.parse(remaining) match {
        case Failure(_) => Success(None, remaining)
        case success => success.map(Option(_))
      })
    }

    def void: Parser[Unit] = {
      this.map(_ => ())
    }


    def oneOrMore(f: (A, A) => A): Parser[A] = {
      new Parser(remaining =>
        this.parse(remaining) match {
          case Success(a, remaining) =>
            def go(rem: String, a: A): Success[A] = {
              this.parse(rem) match {
                case Success(aa, rem) => go(rem, f(a, aa))
                case Failure(_) => Success(a, rem)
              }
            }
            go(remaining, a)
          case failure @ Failure(_) => failure
        }
      )
    }

  }

  object Parser {

    def text(value: String): Parser[String] = {
      new Parser(remaining => if(remaining.startsWith(value)) {
        Success(value, remaining.substring(value.length))
      } else {
        Failure(remaining)
      })
    }

    val end: Parser[Unit] = {
      new Parser(remaining => {
        if(remaining.isEmpty) {
          Success((), "")
        } else {
          Failure(remaining)
        }
      })
    }

    val digits: Parser[Int] = (0 to 9)
      .map(_.toString)
      .map(Parser.text)
      .reduce((x, y) => x.or(y))
      .oneOrMore(_ + _)
      .map(_.toInt)

    // Expr is an algebraic data type
    sealed trait Expr extends Product with Serializable
    final case class Literal(value: Int) extends Expr
    final case class Add(l: Expr, r: Expr) extends Expr
    final case class Multiply(l: Expr, r: Expr) extends Expr

    val plus: Parser[Unit] = text("+").void
    val multiply: Parser[Unit] = text("*").void

    val literalExpr: Parser[Expr] = digits.map(Literal(_))
    def expr: Parser[Expr] = (plusExpr.or(multiplyExpr).or(literalExpr)) <* end
    def plusExpr: Parser[Expr] = literalExpr.combine(plus, expr)((a, _, b) => Add(a, b))
    def multiplyExpr: Parser[Expr] = literalExpr.combine(multiply, expr)((a, _, b) => Multiply(a, b))

  }

}
