package language

import fastparse._, MultiLineWhitespace._
import cats.implicits._

// Solution to 2-names
object LanguageTwo {
  def literal[_: P]: P[Expr] =
    P((minus ~ number).map(x => Literal(-x)) | number.map(Literal(_)))
  def minus[_: P] = P("-")
  def number[_: P]: P[Int] = P(CharIn("0123456789").repX(1).!).map(_.toInt)

  def add[_: P]: P[Expr] =
    P((literal ~ "+" ~ expr)).map{ case (l, r) => Add(l, r) }
  def multiply[_: P]: P[Expr] =
    P((literal ~ "*" ~ expr)).map{ case (l, r) => Multiply(l, r) }
  def expr[_: P]: P[Expr] =
    P((add | multiply | literal))

  def name[_: P]: P[String] =
    P(CharIn("a-zA-Z").! ~ CharIn("a-zA-Z0-9").repX.!)
      .map{ case (char, rest) => char ++ rest }
  def let[_: P]: P[Let] =
    P("let" ~ name ~ "=" ~ expr).map{ case (name, expr) => Let(name, expr) }
  def decl[_: P]: P[Decl] =
    P(let)

  def program[_: P]: P[Program] =
    P((decl | expr).rep ~ End).map( ls => Program(ls.toList) )


  final case class Program(program: List[DeclOrExpr])

  sealed trait DeclOrExpr extends Product with Serializable
  sealed trait Decl extends DeclOrExpr
  final case class Let(name: String, expr: Expr) extends Decl
  sealed trait Expr extends DeclOrExpr
  final case class Literal(value: Int) extends Expr
  final case class Add(l: Expr, r: Expr) extends Expr
  final case class Multiply(l: Expr, r: Expr) extends Expr

  def parse(text: String): Program =
    fastparse.parse(text, t => program(t)) match {
      case Parsed.Success(v, _) => v
      case Parsed.Failure(l, i, e) =>
        throw new Exception(
          s"Could not parse ${text} with label '${l}', index ${i}, and remaining text '${e}'")
    }

  /**
   * Evaluate a program returning the value of the last expression if there is such an expression
   */
  def eval(program: Program): Option[Int] =
    program.program.foldLeft(none[Int]){ (result, declOrExpr) =>
      eval(declOrExpr) match {
        case None => result
        case Some(v) => Some(v)
      }
    }

  def eval(declOrExpr: DeclOrExpr): Option[Int] =
    declOrExpr match {
      case Let(_, _) => None
      case Literal(v) => v.some
      case Add(l, r) =>
        (eval(l), eval(r)).mapN((l, r) => l + r)
      case Multiply(l, r) =>
        (eval(l), eval(r)).mapN((l, r) => l * r)
    }

  def prettyPrint(program: Program): String =
    program.program.map(prettyPrint _).mkString("\n")

  def prettyPrint(declOrExpr: DeclOrExpr): String =
    declOrExpr match {
      case Let(n, e) => s"let ${n} = ${prettyPrint(e)}"
      case Literal(v) => v.toString
      case Add(l, r) => s"${prettyPrint(l)} + ${prettyPrint(r)}"
      case Multiply(l, r) => s"${prettyPrint(l)} * ${prettyPrint(r)}"
    }
}
