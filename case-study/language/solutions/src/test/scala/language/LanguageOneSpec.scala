package language

import hedgehog._
import hedgehog.runner._

object LanguageOneSpec extends Properties {
  import LanguageOne._

  def tests: List[Test] =
    List(property("pretty print is inverse of parse", testPrettyPrintParseInverse))

  def testPrettyPrintParseInverse: Property =
    for {
      depth <- Gen.int(Range.linear(1, 7)).forAll
      expr <- genExpr(depth).forAll
    } yield (prettyPrint(parse(prettyPrint(expr))) ==== prettyPrint(expr))


  def genLiteral: Gen[Expr] =
    for {
      v <- Gen.int(Range.linear(-1000, 1000))
    } yield Literal(v)

  def genAdd(depth: Int): Gen[Expr] =
    for {
      l <- genExpr(depth - 1)
      r <- genExpr(depth - 1)
    } yield Add(l, r)

  def genMultiply(depth: Int): Gen[Expr] =
    for {
      l <- genExpr(depth - 1)
      r <- genExpr(depth - 1)
    } yield Multiply(l, r)

  def genExpr(depth: Int): Gen[Expr] =
    if(depth == 0) genLiteral
    else Gen.choice1(genLiteral, genAdd(depth), genMultiply(depth))
}
