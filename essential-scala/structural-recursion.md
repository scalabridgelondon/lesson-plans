# Structural Recursion

## Overview {:teacher:}

Core concept: transformation of an algebraic data type. The structure of the transform follows the structure of the data.

Scala features:
- pattern matching
- polymorphism

Rule: any time you need to transform an algebraic data type you can implement this transform using structural recursion.


## Introduction {:present:}

Example ADT and transform on it.


## Pattern matching

Pattern matching is a way to work with ADT, making decisions based on the shape of the data. This is different than calling methods or accessing fields on a type, and more powerful at the same time.

The best way to see the difference is to start with an example. We will use this ADT we built in the previous lesson:

```scala
sealed trait Attendee
final case class Student(name: String, goal: String) extends Attendee
final case class Mentor(name: String, yearsOfExperience: Int) extends Attendee
```

Let's imagine that we want to know at which time an `Attendee` to Scala Bridge must be at the location the event takes place. This time will be different for `Student` and `Mentor`. 

If you are used to OO, you could imagine an implementation to solve this problem by using a field in `Attendee`:

```scala
sealed trait Attendee {
   val timeAtLocation: LocalDateTime
}
final case class Student(name: String, goal: String, timeAtLocation: LocalDateTime) extends Attendee
final case class Mentor(name: String, yearsOfExperience: Int, timeAtLocation: LocalDateTime) extends Attendee
```

but this has some unwanted consequences. We must provide the time when we create an instance of `Attendee` and we will have to modify existing code currently using these `case classes`. And what about time for multiple locations? 

A more FP solution would be to implement the following function:

```scala
def timeAtLocation(attendee: Attendee): LocalDateTime = ???
```

which we can call for each `Attendee` for whom we want to check the `timeAtLocation`. But, how do we know if an `Attendee` is an `Student` or a `Mentor`? If you are used to OO you may be thinking on `casting` the type (if you don't know what this means, that's good as it is not a good practice in Scala!). In Scala the solution is `Pattern Matching`:

```scala 
def timeAtLocation(attendee: Attendee): LocalDateTime = attendee match {
   case Student(name, goal) =>  LocalDateTime.of(2019, Month.APRIL, 4, 18, 00)
   case Mentor(name, yearsOfExperience) => LocalDateTime.of(2019, Month.APRIL, 4, 17, 30)
}
```



{:teacher:} See chapter 3.5 of `Essential Scala` for guidance
