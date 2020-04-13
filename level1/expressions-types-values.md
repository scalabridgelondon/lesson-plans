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

*They should be able to reason about this (expressions have types) but could also check it empirically by running the program.*


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

See Creative Scala Chapter 2


## Objects and Method Calls

See Creative Scala Chapter 2


## Operator Notation in Scala

See Creative Scala Chapter 2


## Exercises

See Creative Scala Chapter 2 for additional exercises


1. What is an expression? What is a value? What is an object?


2. What is the type of the following expressions? What value do they evaluate to? Justify your answers.

`4/2`
`2/4`
`1/0`


3. Write code to draw three circles in a row (that is, one circle beside another circle beside another circle). Make the circles different colours.


4. Write a program that draws four circles in a square pattern. Make each circle a different color.

Did you make any errors writing this code? What strategies did you use to fix your errors?


## References

* Chapter 2 of Creative Scala covers this information in more detail.
* On reasoning in software engineering: https://blog.nelhage.com/post/computers-can-be-understood/
* On empiricism in software engineering: https://blog.nelhage.com/post/systems-that-defy-understanding/
