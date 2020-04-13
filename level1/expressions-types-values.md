# Expressions, Types, and Values

## How to Use This Lesson Plan

This lesson plan provides a complete script for running a ScalaBridge session. Normal text, like this, indicate material that you should recite to the students. You can just read it verbatim but the material will be more engaging if you give your own interpretation.

> A blockquote like this is a question to ask of students

In a large group, read the question and then select a person to ask it. Choose a different person each time so that everyone gets asked a question. Don't choose the person until you have asked the question, so everyone has to pay attention in case they are asked. Depending on the situation you could instead ask a pair of people to answer a question.

If the group is small enough you can put questions to the group and tell them to discuss amongst themselves and get back to you with the answer.

*Text in italics like this are notes to you, the mentor.*

**New terms** are introduced in bold.


## Expressions, Evaluation, and Values

In this section we'll introduce the basic building blocks of programs and start building a model of what happens when a computer runs a program.

Take the following program.

```scala
1 + 1
```

It isn't very complicated but we can say a lot about it.

> What is 1 + 1?

*The answer will probably be 2. That's correct in one sense and wrong in another. The goal of the following material to make this difference explicit to the students.*

The answer `2` is what the program evaluates to. **Evaluation** is a fancy word for running a program. So when we run the program `1 + 1` we get the answer `2`. Alternatively when we could say we evaluate the program to get the answer.

However there is a difference between what the program evaluates to and the program itself. `1 + 1` is the program, and `2` is the result of running the program.

In particular `1 + 1` is a part of a program called an **expression**. What defines an expression is that when it is evaluated it produces a **value**. A value is something in the computer's memory. In the case of `1 + 1` is evaluates to something that represents `2` in the computer's memory (in Scala this is a 32-bit signed integer in two's complement representation---which is a lot of jargon that we mention just to show that what is in the computer's memory is not what we say or write down.)

So our model is this:

- An expression is a program that evaluates to a value.
- Evaluation is the process of running a program. We can say evaluation gives a program meaning.
- A value is the result of running a program that resides in the computer's memory.

> What is the program 1 + 1 and what does it evaluate to?

*Hopefully they say it is an expression that evaluates to the value 2.*

> When we read written text that creates an understanding of the text in the mind of the reader. This is analogous to evaluating expressions to yield values. What corresponds to an expression, to evaluation, and to a value in this analogy?

*Written text = expression; reading = evaluation; understanding = value.*


## Types

Every expression has a **type**. Types describe a set of possible values that an expression can evaluate to. For example, the type of `1 + 1` is `Int`, which means that if `1 + 1` evaluates to a value it will be one of the approximately 4 billion values that the computer uses to represent integers.

> How could an expression not evaluate to a value?

*Difficult question as we haven't given the students enough information to come up with this on their own---but they might if they are creative. The answer is that if something goes wrong the expression may not evaluate to a value.*

> What could go wrong with basic arithmetic so that don't get a value? For example, can we always divide two integers.

*Check they know what integers are first. This is a good chance to talk about integer arithmetic. The fact that integers are not automatically converted to doubles may be surprising to students who have exprience with Javascript or other similar languages. There is also division by zero which produces an error.*

A type is a property of an expression, not of a value. If you have used a "dynamically typed" language like Javascript or Python, what they call types apply to values and not to expressions. What they call types are what we call ***tags**. 

> What is the type of `1/0`?

*They should be able to reason about this (expressions have types) but could also check it empirically by running the program.  Discuss this, but don't try to correct their answer.  We haven't yet explained methods*


## Problem Solving Strategies

> Think about how you solved the last question. Can you think of another way you could have solved it?

These strategies are examples of general strategies we apply to problem solving. If we explicitly understand the strategies we can be more efficient in choosing an appropriate strategy to solve any given problem.

Here are three general problem solving strategies:

- Appeal to authority
- Empiricism: inspect the world, or do an experiment and collect data.
- Reasoning: using a model of a process reason about what will happen.

> How can we solve the question of the type of `1 / 0` empirically? How can we solve is by reasoning.

*Empirically: inspect type in IDE or in the console. Reasoning: understand that types apply to expressions and the type of Int / Int is Int.*

> Give some examples of applying these three strategies to programming in general.

*Examples of empiricism and reasoning will probably be a bit hard for true beginners, but see how they get on and make it obvious that they are not expected to know the answers.*

*Appeal to authority: ask a mentor; Google it; search Stack Overflow; read the docs; etc.*

*Empiricism: run the code, debugging, logging, testing.*

*Reasoning: informal reasoning like we did above. Types. Formal methods.*


## Literals

A literal is an expression that evaluates to itself.

Consider:

```
2
```

```
1 + 1
```

> Which of these is a literal expression?
*Hopefully, you'll get the answer 2.  If not, ask the student to evaluate both expressions*


Text is also a literal expression.

```
"To be fond of dancing"
```

> What is the type of this literal?  How did you find this out?

*Hopefully, your student will say this has the type String*
*If not, try and get them to evaluate this expression in a repl and read the type*

## Objects and Method Calls

The operations on an object are called it's methods.

For example `toUpperCase` is an operation on strings.

Methods are written with a `.` in front of them and `()`.  This is known as method call syntax.

```
"titan".toUpperCase
```

> What does this expression evaluate to?  What type does it have?

*Hopefully, your student will say this evaluates to "Titan" has the type String*

Methods can have arguments, also known as parameters

```
"hello".take(3)
```

In this example, `3` is an argument to the method `take`

> What does this expression evaluate to?
*Hopefully, they'll use the repl and get the answer "hel"*

```
"hello".take(2)
```

> What does this evaluate to?
*Hopefully, they'll use the repl and get the answer "he"*
*We're leading towards the idea that a method will evaluate to a different value depending on it's arguments*

> Can you predict what `hello.take(4)` would evaluate to?  What is the meaning of the argument passed to `take`?
*If they can't predict this, get them to evaluate it in the REPL*

Method calls are expressions.  They evaluate to objects.  This means we can chain method calls together.

```
"hello".toUpperCase.take(3)
```

> What does this evaluate to?
*This question is to check their understanding.  Don't go to deeply into models of execution (which happens first), at this point*

## Operator Notation in Scala

A method with one argument can be called with spaces.  This is known as infix notation.

You've already seen several of these

```
1 + 2
```

Consider

```
"hello" take 3
```
> Is this a valid Scala program?
*If you get the answer 'no', encourage the student to evaluate this in the repl.  When the call succeeds, repeat the definition of infix notation*

> Can you write it using `.` and `()` instead of space?
*They might need some help here.  Once they've finished, reiterate the definition of method call sytnax*


> Is `"hello".take(3)` the same as `"hello" take 3`?  Are these the same as `"hello".take(1 + 2)`?
*`"hello".take(3)` and `"hello" take 3` are different notation for the same expression*
*`"hello".take(1 + 2) is a different expression.  We're trying to get them to understand the difference between notation of expressions and expressions themselves*

We can call `1+2` using method syntax

```
1.+(2)
```

> Write `2 / 4` using method syntax
*This is to confirm their understanding*

> Write 1 + 2 + 3 using method syntax
*This is to confirm their understanding of method chaining*

> Can you write `hello.take(3).take(2)` in infix notation?
*This is to confirm their understanding of method chaining*

> Why would you use one notation over the other?

*If this is a group, try and get them to discuss which style they prefer*

Programs are meant to be read by people, as well as evaluated by computers.  `1 + 2` tends to be easier to read than `1.+(2)`

## Types

Types stop us from calling methods that don't exist.

The type of an expression tells Scala what methods can exist, and what they evaluate to.

Here are some examples:

```
"Bronte" / "Austen"
```

> What is the type of this expression?  What do you think this expression will evaluate to?

*The expression doesn't have a type. By the same token, it can't be evaluated*

Consider

```
"Bronte".take("Austen")
```

>  What do you think this expression should evaluate to?

*This expression also can't be evaluated*

Methods arguments also have types.

Consider

```
"Bronte".take(1 + 2)
```

> Is it possible to run this expression?

*A difficult question, as we haven't explained that arguments can be expressions*

Method arguments are also expressions.  This means they can take in expressions such as `1 + 2`, as well as literal expressions such as `3`.

```
"Bronte".take(1 / 0)
```

> Is it possible to run this expression?
*Yes (hopefully, they'll try to do so).  The result will be an error*

> What is the difference between the error in `"Bronte".take("Austen")` and `"Bronte".take(1 / 0)`?
*One is an error that prevents evaluation from happening, while the other is an error that happens during evaluation.  Try to avoid references to compile time and run time at this point.*

## Exercises

See Creative Scala Chapter 2 for additional exercises

1. What is an expression? What is a value? What is an object?

1. Identify the values, objects and expressions in the following code.

```
"hello".take(1 + 2).toUpperCase
```

Which parts use method call syntax?  Which use infix notation?

Can you write this completely in method call syntax?


2. What is the type of the following expressions? What value do they evaluate to? Justify your answers.

`4/2`
`2/4`
`1/0`

3.  What is the type of `1 / "Bronte"`?  How does it differ to the type of `1 / 0`?

Did you make any errors writing this code? What strategies did you use to fix your errors?


## References

* Chapter 2 of Creative Scala covers this information in more detail.
* On reasoning in software engineering: https://blog.nelhage.com/post/computers-can-be-understood/
* On empiricism in software engineering: https://blog.nelhage.com/post/systems-that-defy-understanding/
