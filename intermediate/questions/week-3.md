# Intermediate Week 3

## Reading

Your reading for this week is

- Chapter 4 of Essential Scala, from 4.5 onwards

Doing the exercises in the book chapter is optional, but likely to be useful.

In this week we're going to work on a slightly longer example than before.


## Questions

In groups we'll answer the following questions:


1. Last week, in question 9, you were asked to implement a data structure to represent an arithmetic expression. Now write a method that evaluates arithmetic expressions. Given an expression it should return a number (which will probably be `Double` or `Int` depending on design choices you made) that the expression evaluates to.

What strategy should you use to implement this?


2. We're now going to implement a technique for _parsing_ text, know as _regular expressions_. We're then going to parse arithmetic expressions into the data structure we've just built and evaluate these expressions. When we've finished we will have built the machinery to take a `String` like "1 + 1" and end up with the value 2. This might not seem like much but we will have actually implemented a tiny programming language!

What does parsing mean? It means to break something into its component parts. We usually talk about parsing text, but we can also talk about parsing visual scenes or any other kind of structured input where that structure is not immediately apparent. For example, given the `String` "1 + 2" we might parse "1" and "2" as literals, and the entire expression as the addition of two literals.

What are regular expressions? They are a simple method of matching a sequence of symbols. In our case our symbols will be characters in a `String`. The simplicity of regular expressions makes them easy to analyse and implement, but limits what they can do. Nonetheless they are very useful. Regular expressions are supported directly by many languages, including Scala, but we're going to build our own very simple regular expression implementation.

For our purpose a regular expression is:

- a literal, which contains a `String` and means to match exactly that `String`;
- alternation, which contains two regular expressions and matches if either of the regular expressions it contains matches;
- sequencing, which contains two regular expressions and matches if the first regular expression matches followed by the second; and
- repetition, which contains a regular expression and matches if the regular expression it contains matches 0 or more times.

What strategy could you use to express this description in code? Implement it.


3. Continuing our regular expression example, we are now going to implement a method `matches` that accepts a `String` and returns a `Boolean` indicating if the `String` matched or not.

What strategy should we use to implement this?

Tip: the `startsWith` method of `String` returns `true` if the `String` starts with the given `String` parameter. E.g. `"ScalaBridge".startsWith("Scala")` is `true` while `"ScalaBridge".startsWith("Java")` is `false`.

Tip 2: the `drop(n)` method on `String` returns the rest of the a `String` without the first `n` characters. E.g. `"Ziggy Stardust".drop(6)` is `"Stardust"`

Tip 3: when matching repetitions you will have to make a decision as to how many repetitions to match. The usual answer is to be _greedy_: match as many as possible.

Show your parser works by creating a regular expression that matches a number: one or more repetitions of the characters '0' to '9' (optionally, allow a decimal point and a fractional part as well).


4. Scala has a built-in type called `Option`. An `Option` is either `None`, and contains no value, or is `Some` and holds a value. Thus we use `Option` to indicate when we have done something that might not yield a value. Parsing is a good example! When we parse a `String` our parse might fail, in which case we return `None`. Otherwise we could return information about what we matched.

What strategy would you use to implement `Option`? Is this what is used in the standard library?


5. Implement a method `match` that returns an `Option` containing a `String`. (We write this type `Option[String]`). If we match a portion of a `String` we return that portion (wrapped in `Some`). Otherwise we return `None`.

What strategy should we use to implement this?


6. Implement a regular expression that consumes white space: a space or a tab character (written '\t')


7. Now we are ready to finish the task. Implement a method that uses regular expressions to match expressions of the form:

<literal> <ws> <operator> <ws> <literal>

Where:

- <literal> is a number
- <ws> is optional whitespace
- <operator> is an arithmetic operation ("+", "*", etc.)

Upon a successful match construct an instance of the expression type you implemented last week.

Tip: `String` has several methods to convert to an `Int` or `Double`: `toInt`, `toIntOption`, `toDouble`, and `toDoubleOptions`. (What do you think the difference is between these methods?)


8. Wire everything together so that you can go from a `String` to an `Option[Double]` (or `Option[Int]` if you only parse `Int`). You have now implemented a tiny programming language! If fact we have implemented two: our regular expression langauge and our arithmetic language.


9. Our current language is limited to expressions that have literal operands. We can parse "1 + 2" but not "1 + 3 + 2". We could write one regular expression to recognise the former. Could we write one regular expression that would recognise the latter? Why or why not?
