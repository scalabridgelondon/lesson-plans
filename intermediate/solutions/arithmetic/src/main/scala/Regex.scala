package arithmetic

sealed trait Regex extends Product with Serializable {
  import Regex._

  /**
   * Match this regular expression zero or more times
   */
  def `*`: Regex =
    Repeat(this)

  /**
   * Match this regular expression one or more times
   */
  def `?`: Regex =
    Sequence(this, Repeat(this))

  /**
   * Match this regular expression and then the given argument
   */
  def ~(that: Regex): Regex =
    Sequence(this, that)

  /**
   * Match this regular expression or the given argument
   */
  def |(that: Regex): Regex =
    Alternative(this, that)

  def parse(input: String): Option[String] =
    this match {
      case Literal(v) => if(input.startsWith(v)) Some(v) else None
      case Sequence(f, s) =>
        // Monadic
        f.parse(input) match {
          case Some(m1) => s.parse(input.drop(m1.length)) match {
            case Some(m2) => Some(m1 ++ m2)
            case None => None
          }

          case None => None
        }
      case Alternative(l, r) =>
        l.parse(input) match {
          case Some(m) => Some(m)
          case None => r.parse(input)
        }

      case Repeat(r) =>
        r.parse(input) match {
          case Some(m1) =>
            val next = input.drop(m1.length)
            this.parse(next) match {
              case Some(m2) => Some(m1 ++ m2)
              case None => Some(m1)
            }
          case None => Some("")
        }
    }

  /**
   * Return the remainder of the input after removing the portion this regular
   * expression matches. Returns the input if this regular expression does not
   * match the input.
   */
  def remainder(input: String): String =
    this.parse(input) match {
      case Some(v) => input.drop(v.length)
      case None => input
    }
}
object Regex {
  final case class Literal(value: String) extends Regex
  final case class Sequence(first: Regex, second: Regex) extends Regex
  final case class Alternative(first: Regex, second: Regex) extends Regex
  final case class Repeat(regex: Regex) extends Regex

  def literal(value: String): Regex =
    Literal(value)

  /**
   * Match one of the given characters in the `String`
   */
  def oneOf(chars: String): Regex =
    if(chars.isEmpty()) throw new Exception("You must supply at least one element to Regex.oneOf")
    else {
      val first = chars.head
      val rest = chars.tail
      rest.toList.foldLeft(Regex.literal(first.toString)){ (accum, char) =>
        accum | literal(char.toString)
      }
    }
}
