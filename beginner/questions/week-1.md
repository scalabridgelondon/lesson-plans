# Week 1

## Reading

Your reading for this week is:

- Chapter 2 of Creative Scala
- Chapter 4 of Creative Scala
- If you need more information on Doodle, the library for drawing pictures, read Chapter 3. This is optional.

Doing the exercises in the book chapter is optional, but likely to be useful.


## Questions

In groups we'll answer the following questions:

1. What is an expression? What is a value? What is an object?


2. What is the type of the following expressions? What value do they evaluate to? Justify your answers.

`4/2`
`2/4`
`1/0`


3. Write code to draw three circles in a row (that is, one circle beside another circle beside another circle). Make the circles different colours.


4. We'll make many mistakes when programming. We need to develop strategies to correct these mistakes. We'll start by looking at three questions. Who could you answer these questions? Are there general strategies here?

- Where is the bathroom?
- What do the angles inside a triangle sum to?
- What is the weather like right now?


5. Which strategies did you use when answering question 2? Can you use other strategies to answer question 2?


6. What is a declaration? What is a top-level declaration?


7. Which of the following are valid top-level declarations?

```scala
val x = 42
```

```scala
object TheAnswer {
  val x = 42
}
```

```scala
40 + 2
```


8. Write a program that draws three circles in a row. That is, a circle beside a circle beside a circle. Make each circle a different color.

Did you make any errors writing this code? What strategies did you use to fix your errors?


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
