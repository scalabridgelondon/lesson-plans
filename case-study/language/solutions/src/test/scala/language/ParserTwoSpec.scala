package language

import minitest._

object ParserTwoSpec extends SimpleTestSuite {

  import ParserTwo._


  test("parse a single char") {
    assertEquals(Parser.char('1').parse("123"), Result.Success('1', "23"))
  }

  test("fail on a char") {
    assertEquals(Parser.char('1').parse("23"), Result.Failure("23"))
  }

  test("parsers should compose with and") {
    assertEquals(
      (Parser.char('1') ~ Parser.char('2')).parse("123"),
      Result.Success(('1', '2'), "3")
    )
  }

  test("parsers should compose with or") {
    val parser = (Parser.char('1') | Parser.char('2'))
    assertEquals(
      parser.parse("1"),
      Result.Success('1', "")
    )
    assertEquals(
      parser.parse("2"),
      Result.Success('2', "")
    )
  }

  test("parsers should repeat") {
    val text = (0 to 30).foldLeft("a")((b, _) => b + "a")
    val parser = Parser.char('a').repeat
    assertEquals(
      parser.parse(text),
      Result.Success(('a', (0 to 30).map(_ => 'a').toList), "")
    )
  }

  test("parsers should map") {
    val parser = Parser.char('a')
    assertEquals(
      parser.map(a => a.toString + "b").parse("abc"),
      Result.Success("ab", "bc")
    )
  }

  test("parse digits") {
    assertEquals(
      ParserExample.digits.parse("123123a"),
      Result.Success(123123, "a")
    )
  }

  test("parse plus") {
    assertEquals(
      ParserExample.plusExpr.parse("123+245"),
      Result.Success(Expr.Add(Expr.Lit(123), Expr.Lit(245)), "")
    )
  }


  test("parse multiply") {
    assertEquals(
      ParserExample.multiplyExpr.parse("123*245"),
      Result.Success(Expr.Mul(Expr.Lit(123), Expr.Lit(245)), "")
    )
  }

  test("arbitary expr") {
    assertEquals(
      ParserExample.expr.parse("123*245+32*98+2"),
      Result.Success(
        Expr.Add(Expr.Mul(Expr.Lit(123), Expr.Lit(245)),
          Expr.Add(Expr.Mul(Expr.Lit(32), Expr.Lit(98)), Expr.Lit(2))), "")
    )
  }
}
