# Arithmetic

Our first language will work parse and interpret arthimetic expressions. This will get us the basic framework for parsing and representing programs.

## Setup

Step zero is to setup a project. You'll want to add some dependencies:

- FastParse: http://www.lihaoyi.com/fastparse/
- Cats: https://typelevel.org/cats/

At this stage we're assuming you're comfortable creating Scala projects with your own preferred setup, but you can grab the setup from the solutions if you want.


## Numbers

Our very first interpreter will parse numbers and evaluate them to ... the number they represent. In more formal terms our language will recognise exactly one type of expression, a literal integer. We will parse these literals into a data structure, our abstract syntax tree (AST). We will then have an interpreter that evaluates the AST to produce a value. Since we only have one type of expressions we only need to represent one type of value.


## Expressions

Our next step is to extend our language with arithmetic expressions. To start with we'll just allow addition and subtraction of literals. More formally we'll have the grammar

Expr :=
	Literal  # This is what we've already implemented
  | Literal + Literal
  | Literal * Literal

At the moment you don't need to worry about precedence.

## Questions


1. What is a program? What is an interpreter?


2. Without running the program, what would the AST of the expression `1 + 2 * 56 + 8`? Is it what you would expect?


3. Write a function `prettyPrint` which takes in an `Expr` such as `Add(Literal(1), Literal(2))` and returns the `String` result `1 + 2`.
   What is the result of the following snippet?
   ```scala
	   val expr = "1 + 2"
	   expr == prettyPrint(parse(expr))
   ```
   Will this always be true?

4. Discuss the similarities between the signature and structure of `prettyPrint` and `eval`.
   Could you call `prettyPrint` an interpreter? Why or why not?



4. Based on the answers to `2.` and `3.` how would you conceptually deal with precedence?
