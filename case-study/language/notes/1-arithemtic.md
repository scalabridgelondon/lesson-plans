# Notes on Arithmetic Language

There are many goals to this exercise. Here we talk about the main ones.

A primary goal is to demystify what makes up a language, and hence gain a better understanding of programming and programming languages. Learning the terns parser, abstract syntax tree, interpreter, and so on are steps along this journey that students without a CS background will need to make.

Picking up a new library, such as FastParse, is something developers will regularly have to do. FastParse has better documentation than most. If the students read enough of it they will find examples that almost solve all the problems we're looking at. This is fair game---in real development you can copy!

Hopefully the student will recognise that the AST (`Expr`) is an algebraic data type, and hence all the interpreters can be structural recursions. If not, ask probing questions ("What strategy are you using here?", "What strategies do you know?", "Here are some strategies. Which ones do you think you are using?") until they make that connection.

There are a lot of subtleties in parsing, such as avoiding excessive back-tracking, that we aren't really interested in. That's why we are avoiding precedence and saying they should just go left ot right.

Generative or property-based testing is a great fit for this problem. Ask the students how they could test the code, and perhaps introduce property based testing. There are some very simple tests in the solution.
