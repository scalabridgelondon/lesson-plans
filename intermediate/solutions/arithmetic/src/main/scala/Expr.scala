package arithmetic

sealed trait Expr extends Product with Serializable {
  import Expr._

  def +(that: Expr): Expr = Add(this, that)
  def -(that: Expr): Expr = Minus(this, that)
  def *(that: Expr): Expr = Multiply(this, that)
  def /(that: Expr): Expr = Divide(this, that)

  def eval: Double =
    this match {
      case Literal(v) => v
      case Add(l, r) => l.eval + r.eval
      case Minus(l, r) => l.eval - r.eval
      case Multiply(l, r) => l.eval * r.eval
      case Divide(l, r) => l.eval / r.eval
    }

  def prettyPrint: String =
    this match {
      case Literal(v) => v.toString
      case Add(l, r) => s"${l.prettyPrint} + ${r.prettyPrint}"
      case Minus(l, r) => s"${l.prettyPrint} - ${r.prettyPrint}"
      case Multiply(l, r) => s"${l.prettyPrint} * ${r.prettyPrint}"
      case Divide(l, r) => s"${l.prettyPrint} / ${r.prettyPrint}"
    }
}
object Expr {
  final case class Literal(value: Double) extends Expr
  final case class Add(l: Expr, r: Expr) extends Expr
  final case class Minus(l: Expr, r: Expr) extends Expr
  final case class Multiply(l: Expr, r: Expr) extends Expr
  final case class Divide(l: Expr, r: Expr) extends Expr

  def literal(value: Double): Expr =
    Literal(value)
}
