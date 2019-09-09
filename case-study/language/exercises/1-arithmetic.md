# Arithmetic

Our first language will parse and interpret arthimetic expressions. This will get us the basic framework for parsing and representing programs.

What is a parser? It is the part of a programming language that reads in the program (perhaps from a file or a `String`) and turns it into some internal representation that can processed by other tools. The common tool that will work with this internal representation is an interpreter (or, perhaps, a compiler) which will run the program. Many languages also provide other tools, such those that provide auto-complete or format code, based on the same internal representation.

So more concretely our first language will consist of two parts:

- a parser to read a `String`, that specifies a number, and turn the input into some data structure; and
- an interpreter to run that data structure, which in this particular case means returning the `Int` the input represents.


## Setup

Step zero is to setup a project. You'll want to add some dependencies:

- FastParse: http://www.lihaoyi.com/fastparse/
- Cats: https://typelevel.org/cats/

At this stage we're assuming you're comfortable creating Scala projects with your own preferred setup, but you can grab the setup from the solutions if you want.


## Numbers

Our very first interpreter will parse numbers and evaluate them to ... the number they represent. In more formal terms our language will recognise exactly one type of expression, a literal integer. We will parse these literals into a data structure, our abstract syntax tree (AST). We will then have an interpreter that evaluates the AST to produce a value. Since we only have one type of expressions we only need to represent one type of value.

More concretely, we need to implement

- a method `parse: String => Expr`, where `Expr` is the type of our AST; and
- a method `eval: Expr => Int ` that evaluates an `Expr`.

Use FastParse to implement the parser. We're not going to tell you how. Part of the challenge of this case study is learning how to use new libraries. FastParse has good, but not great, documentation.

An example program this language can handle is `42`.


## Expressions

Our next step is to extend our language with arithmetic expressions. To start with we'll just allow addition and subtraction of literals. So now an expression is either a literal (which we have already implemented) or addition of two literals, or multiplication of two literals. More formally we'll have the grammar

   Expr :=
	   Literal  # This is what we've already implemented
     | Literal + Literal
     | Literal * Literal

At the moment you don't need to worry about precedence. Just parse left-to-right, so expressions on the left occur before those on the right.

Extend `parse` and `eval` to support this new language. What strategy did you use to implement `eval`?

An example program this language can handle is `41 + 1`.


## Recursion

Our final step is to add recursion to our definition, so that an expression can in turn can contain expressions. This allows us to write expressions like `10 * 2 + 20 + 2`. More formally we'll have the grammar

   Expr :=
	   Literal  # This is what we've already implemented
     | Expr + Expr
     | Expr * Expr
     
You may with to restrict one of the branches of both addition and multiplication to be a literal, when you come to implement the grammar (why?)

Extend `parse` and `eval` to support this new language. What strategy did you use to implement `eval`?


## Questions

1. What is a program? What is an interpreter?


2. Without running the parser, what would you expect the AST of the expression `1 + 2 * 56 + 8` to be? Now run `parse` on this expression. Is it what you expected?


3. Write a function `prettyPrint` which takes in an `Expr` such as `Add(Literal(1), Literal(2))` and returns the `String` result `1 + 2`.
   What is the result of the following snippet?
   ```scala
	   val expr = "1 + 2"
	   expr == prettyPrint(parse(expr))
   ```
   Will this always be true?
   
   What uses can you think of for `prettyPrint`.


4. Discuss the similarities between the signature and structure of `prettyPrint` and `eval`.
   Could you call `prettyPrint` an interpreter? Why or why not?


5. Based on the answers to `2.` and `3.` how would you conceptually deal with precedence?
