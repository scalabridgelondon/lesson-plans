# Intermediate Week 1

## Reading

Your reading for this week is

- Chapter 2 of Essential Scala

Doing the exercises in the book chapter is optional, but likely to be useful.


## Questions

In groups we'll answer the following questions:

1. What is an expression? What is a value? What is an object? What is a literal? What is a method? Can you give an example of each?


2. What is operator notation? How does it differ from normal method call syntax?


3. Declare an object called `ScalaBridge` with:
- a field, called `theAnswer`, given the value `42`; and
- a method called `hello` that accepts two parameters called `name` and `answer`. The parameter called `name` should be a `String` and the parameter called `answer` should be an `Int`. The method should return a `String` that reads "Hello <name>, the answer is <answer>" (replacing `name` and `answer` with the appropriate values).


4. What is a type? What is the type of the following expressions? What value do they evaluate to? Justify your answers.

`4/2`
`2/4`
`1/0`


5. We'll make many mistakes when programming. We need to develop strategies to correct these mistakes. We'll start by looking at three questions. How could you answer these questions? Are there any general strategies here?

- Where is the bathroom?
- What do the angles inside a triangle sum to?
- What is the weather like right now?


6. Which strategies did you use when answering question 4? Can you use other strategies to answer question 4?


7. What are the differences between a declaration and an expression? What can each do that the other cannot? Why do we have these different parts of programs?


8. `if` is an expression in Scala, but this is not the case in many other languages. For example, it is not in Python and Javascript. What advantages are there to `if` being an expression?


9. Consider the following code

```scala
import doodle.image._

object AnExample {
  val image = Image.square(100).fillColor(Color.blue)
  <A>
}

object SomethingElse {
  val ziggy = "Ziggy Stardust"
  <B>
}
```

How is the name `Image` brought into scope?
What names are in scope at the point `<A>`?
What names are in scope at the point `<B>`?
What strategies could you use to solve this question?


10. What is the type of `Image.circle(100)`? What is the type of `Image.circle(100).draw()`? What is the type of `4 + 2`? What is the type of `println(4 + 2)`? There is a big differences between the expressions

```scala
Image.circle(100)
4 + 2
```

and

```scala
Image.circle(100).draw()
println(4 + 2)
```

that goes beyond their type. What is it? Hint: does it change a program if we replace `4 + 2` with `6`? What about replacing `println("Hi!")` with the value it evaluates to?


11. Types are always an approximation to program behaviour. Why must they be an approximation? Can you think of a program that demonstrates this? Hint: would it be possible to prevent division be zero at compile time in all possible programs?
