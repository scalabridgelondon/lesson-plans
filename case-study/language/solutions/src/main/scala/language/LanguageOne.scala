package language

import fastparse._, SingleLineWhitespace._

// Solution to 1-arithmetic
object LanguageOne {
  def literal[_: P]: P[Expr] = P((minus ~ number).map(x => Literal(-x)) | number.map(Literal(_)))
  def minus[_: P] = P("-")
  def number[_: P]: P[Int] = P(CharIn("0123456789").rep(1).!).map(_.toInt)

  def add[_: P]: P[Expr] = P((literal ~ "+" ~ expr)).map{ case (l, r) => Add(l, r) }
  def multiply[_: P]: P[Expr] = P((literal ~ "*" ~ expr)).map{ case (l, r) => Multiply(l, r) }
  def expr[_: P]: P[Expr] = P((add | multiply | literal) ~ End)


  // Expr is an algebraic data type
  sealed trait Expr extends Product with Serializable
  final case class Literal(value: Int) extends Expr
  final case class Add(l: Expr, r: Expr) extends Expr
  final case class Multiply(l: Expr, r: Expr) extends Expr

  def parse(text: String): Expr =
    fastparse.parse(text, t => expr(t)) match {
      case Parsed.Success(v, _) => v
      case Parsed.Failure(l, i, e) =>
        throw new Exception(
          s"Could not parse ${text} with label '${l}', index ${i}, and remaining text '${e}'")
    }

  def eval(expr: Expr): Int =
    expr match {
      case Literal(v) => v
      case Add(l, r) => eval(l) + eval(r)
      case Multiply(l, r) => eval(l) * eval(r)
    }

  // For fun
  def prettyPrint(expr: Expr): String =
    expr match {
      case Literal(v) => v.toString
      case Add(l, r) => s"${prettyPrint(l)} + ${prettyPrint(r)}"
      case Multiply(l, r) => s"${prettyPrint(l)} * ${prettyPrint(r)}"
    }
}
