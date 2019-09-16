package arithmetic

object Parser {
  val double = Regex.oneOf("0123456789").? ~ Regex.literal(".") ~ Regex.oneOf("0123456789").?
  val int = Regex.oneOf("0123456789").?
  val number = int | double

  val operator = Regex.oneOf("+-*/")

  val whitespace = Regex.oneOf(" \t").*

  def parse(string: String): Expr = {
    val input = string.trim // Easier than parsing whitespace regex
    number.parse(input) match {
      case Some(l1) =>
        val input2 = whitespace.remainder(input.drop(l1.length))
        operator.parse(input2) match {
          case Some(op) =>
            val input3 = whitespace.remainder(input2.drop(op.length))
            number.parse(input3) match {
              case Some(l2) =>
                op match {
                  case "+" => Expr.literal(l1.toDouble) + Expr.literal(l2.toDouble)
                  case "-" => Expr.literal(l1.toDouble) - Expr.literal(l2.toDouble)
                  case "*" => Expr.literal(l1.toDouble) * Expr.literal(l2.toDouble)
                  case "/" => Expr.literal(l1.toDouble) / Expr.literal(l2.toDouble)
                }

              case None => throw new Exception(s"$string does not end with a literal number")
            }

          case None => throw new Exception(s"$string does not contain an operator")
        }

      case None => throw new Exception(s"$string does not start with a literal number")
    }
  }
}
