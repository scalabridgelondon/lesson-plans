# Structural Recursion over the Natural Numbers

## Reading

Read chapters 3, 4, and 5. We've covered most of this material already but we need to fill in details and get some more practice. Skim the parts you feel you know well.


## Methods {:present:}

Binding a name to a value (using `val`) allows us to simplify repeated expressions, but it doesn't handle the case where we have expressions that are the same in some ways but differ in others.

For example, if we wanted to create squares with different colours we could write

```scala
Image.square(100).fillColor(Color.royalBlue)
Image.square(100).fillColor(Color.gold)
```

Using a `val` we could write

```scala
val sq = Image.square(100)
sq.fillColor(Color.royalBlue)
sq.fillColor(Color.gold)
```

but we still have repetition that we can't remove. A method allows us to do this.

```scala
def colouredSquare(colour: Color): Image =
  Image.square(100).fillColor(colour)

colouredSquare(Color.royalBlue)
colouredSquare(Color.gold)
``**

Now we've removed all repetition (though in this simple example the code is actually longer :-)

We need to understand two things:
* semantics; and
* syntax.

**TODO: discuss**


### Exercises

Abstract over expressions. Examples:
- change palette of fabric design
- change basic shapes of fabric design


## Structural Recursion over Natural Numbers

* Introduce structural recursion over natural numbers w/ the lego example
* Show how we write this in Scala
* Example semantics and syntax of pattern matching
* Emphasise you don't have to understand recursion (try to do substitution in your head---your head will have a stack overflow). You just have to get the individual cases correct and the recursion is guaranteed correct.
