package language

import cats.implicits._
import hedgehog._
import hedgehog.runner._

object LanguageTwoSpec extends Properties {
  import Hedgehog._
  import LanguageTwo._

  def tests: List[Test] =
    List(property("pretty print DeclOrExpr is inverse of parse",
                  testDeclOrExprPrettyPrintParseInverse),
         property("pretty print Program is inverse of parse",
                  testProgramPrettyPrintParseInverse))

  def testDeclOrExprPrettyPrintParseInverse: Property =
    for {
      declOrExpr <- genDeclOrExpr.forAll
    } yield (prettyPrint(parse(prettyPrint(declOrExpr))) ==== prettyPrint(declOrExpr))

  def testProgramPrettyPrintParseInverse: Property =
    for {
      program <- Gen.list(genDeclOrExpr, Range.linear(1, 100)).forAll
    } yield (prettyPrint(parse(prettyPrint(Program(program))))) ==== prettyPrint(Program(program))


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
    if(depth <= 0) genLiteral
    else Gen.choice1(genLiteral, genAdd(depth), genMultiply(depth))

  def genName: Gen[String] =
    (genAlpha, Gen.list(genAlphaNumeric, Range.linear(1, 100))).mapN{ (first, rest) =>
      (first +: rest).mkString
    }

  def genAlpha: Gen[Char] =
    Gen.choice1(Gen.char('a', 'z'), Gen.char('A', 'Z'))

  def genAlphaNumeric: Gen[Char] =
    Gen.choice1(Gen.char('a', 'z'), Gen.char('A', 'Z'), Gen.char('0', '9'))

  def genDecl(depth: Int): Gen[Decl] =
    for {
      name <- genName
      expr <- genExpr(depth)
    } yield Let(name, expr)

  def genDeclOrExpr: Gen[DeclOrExpr] =
    for{
      depth <- Gen.int(Range.linear(1, 7))
      declOrExpr <- Gen.choice1(genDecl(depth).map(x => x : DeclOrExpr),
                                genExpr(depth).map(x => x : DeclOrExpr))
    } yield declOrExpr
}
