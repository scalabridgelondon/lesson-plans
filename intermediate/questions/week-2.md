# # Intermediate Week 2

## Reading

Your reading for this week is

- Chapter 3 of Essential Scala, and Chapter 4 up to 4.5

Doing the exercises in the book chapter is optional, but likely to be useful.


## Questions

In groups we'll answer the following questions:

1. What is a class? How does it relate to an object? What is a constructor?


2. Define a class to represent a pet. There are no specific criteria but make some reasonable choices in your design. Create some instances of this class.


3. What is the type of `throw new Exception("Who dis?")`? Why? What strategies can you use to answer this question?


4. Under what conditions would it be idiomatic to represent pets with case classes in Scala?


5. For this exercise you will need Doodle 0.9.7 or above. You may want to the use `creative-scala-template` project as it sets up everything you will need. You will want the following imports in your code

```scala
import doodle.core._
import doodle.image.Image
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.reactor._
import scala.concurrent.duration._
```

In Doodle there is a trait called `Reactor`. A `Reactor` allows us to define an animation in terms of three things:

- a value called `initial` that determines the starting state;
- a method `onTick` that determines how we transform the current state to the new state on each clock tick; and
- a method `render` that determines how we represent the state as an `Image` that we can draw.

Create an animation, using a `Reactor` that displays a circle that moves in a circular orbit.

- The initial state is the `Angle` `0.degrees`.
- At each clock tick move the angle 5 degrees. Pro tip: you can add an `Angle` to an `Angle`.
- To render the circle draw a circle at the point that is 300 pixels from the origin and rotated by the angle. Pro tip 1: You can create a point in Doodle with the syntax `Point(r, a)` where `r`, a `Double`, is the distance from the origin and `a` is an `Angle`. Pro tip 2: You can position a circle at a `Point` with the expression `Image.circle(10).at(point)`

Further pro tips:

- you'll have to implement some other methods in `Reactor`. You can choose what you do for them
- here is how to create a `FiniteDuration`: `FiniteDuration(100, MILLISECONDS)`


6. Algebraic data types are really really really important (really!) What is an algebraic data type at a *conceptual* level? How do we implement an algebraic data type in Scala? (Do you know any other languages that support algebraic data types?)


7. There are many types of stars. For our purposes we will consider four. A star can be either:

- a main sequence star (such as our sun);
- a red giant;
- a supernova (kablooie!); or
- a neutron star

What strategy would you use to represent this in Scala? Make it so.


8. One possible lifecycle for a star is to transition from a main sequence star (small and yellow), to a red giant (big and red), to a supernova (big and explosion looking), to a neutron star (very small and white). Change your animation from question 6 so that the orbiting circle also changes to represent the lifecycle of a star. It should start as a main sequence star and eventually end up as a neutron star. When these transitions occur is up to you, as is the exact appearance of the star (maybe you want to make it grow and shrink in increments, and slowly change colour, for example).


9. An arithmetic expression can be either:

- a literal number; 
- a addition, which has a left and right operands (both of which are expressions); or
- a multiplication which has a left and right operands (both of which are expressions).

Implement this data structure. What strategy did you use?


10. Using your answer to question 9 represent the following expressions:

- 1000
- 1 + 41
- 1 * 2 + 10 + 30
