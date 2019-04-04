# Structural Recursion

## Overview {:teacher:}

Core concept: transformation of an algebraic data type. The structure of the transform follows the structure of the data.

Scala features:
- pattern matching
- polymorphism

Rule: any time you need to transform an algebraic data type you can implement this transform using structural recursion.


## Introduction {:present:}

After the previous lesson you now understand ADT and how to model your domain using them. But, of course, we don't want just to model a domain. We want to do things with it, and get results operating with these ADT.

In today's lesson we will start looking at *polymorphism*, which helps us work with our model while we avoid duplication in our code. Later we will look at *pattern matching*, a way to interact with an ADT based on the shape of the data.

{:teacher:} beware parametric polymorphism, it can be harded to understand than it seems, depending on previous programming experience. Don't introduce implicits here, it may confuse the issue more.

## Polymorphism

Polymorphism, at its core, is the capability of either:

- call a function with arguments of different types (also known as function overload)
- write code targeting an interface that is shared across several classes or records, usually via subtyping
- write code that can work with many different values at the same time (parametric polymorphism)

We will talk about how Scala implements both.

### Ad hoc polymorphism

[Ad hoc plymorphism](https://en.wikipedia.org/wiki/Ad_hoc_polymorphism), also known as `function overload` is one of the firsts types of polymorphism a developer encounters.

Let's say you have a function `add` to sum two numbers. You can implement it for `Int`, `Long`, `Double`, etc as follows:

```scala
def add_int(a: Int, b: Int): Int = a + b
def add_long(a: Long, b: Long): Long = a + b
def add_double(a: Double, b: Double): Double = a + b
```

This code works, but it is tedious to remember and type those `_int` and `_long`. A solution provided by languages is the fact that they allow you to *overload* a function, so you can have a function with the same name but accepting different parameters. The compiler then chooses the correct one at compile time.

```scala
def add(a: Int, b: Int): Int          = a + b
def add(a: Long, b: Long): Long       = a + b
def add(a: Double, b: Double): Double = a + b
```

This is a simple example just to understand the concept, in later lessons we will see how you can use `Type classes` and `implicits` for more powerful ad hoc polymorphism.

{:prompt:} Implement an ad hoc polymorphic function to concatenate several elements in a `String`. The function must have at least 3 input parameters.

{:teacher:} Don't spend too much time here, as the other sections are more relevant for project work.

### Subtyping polymorphism

[Subtyping](https://en.wikipedia.org/wiki/Subtyping) is a type of polymorphism in which we refer to a type that shares a common interface with many other types. In OO this is usually achieved by inheritance. In Scala we favour composition via traits.

```scala
trait HasName {
  def name: String
}
class Person(name: String, age: Int) extends HasName
class Pet(name: String) extends HasName
```

In this hierarchy we have a parent trait `HasName` that provides an abstract method `name`. All classes that implement that trait will have to implement that method too. This subtyping allow us to implement functions that target the trait but will work with any class implenting it:

```scala
def sayHi(hasName: HasName): String = s"Hi, ${hasName.name}!"

val person = new Person("Ewa", 25)
val pet = new Pet("Maple")

sayHi(person) // Hi, Ewa!
sayHi(pet) // Hi, Maple!
```

As you can see from the above, our function `sayHi` works with any implementor of `HasName` and has access to any methods defined in the trait. It is important to note that in the implementation we don't have access to methods not defined in the trait, so (for example) we can't access the `age` field of `Person`.

{:prompt:} create a hierarchy using a trait with a method `def forPrinting: String` that will return a `String` representation of the implementor. Implement a function that receives the trait and uses `forPrinting` to print the representation to console.

{:teacher:} highlight how the ADT they created last lesson are a type of subtyping

### Parametric polymorphism

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

By using the `match` and `case` keyword we create a piece of code that changes behaviour based on the `shape` of the ADT we provide to the function. Note that we have access to the fields in the ADT, so we can use them in our logic. For example, we could print to console details about the `Attendee` as we make the decision: 

```scala 
def timeAtLocation(attendee: Attendee): LocalDateTime = attendee match {
   case Student(name, goal) =>  
      println(s"Student: $name - $goal will be there at 18:00")
      LocalDateTime.of(2019, Month.APRIL, 4, 18, 00)
   case Mentor(name, yearsOfExperience) => 
      println(s"Mentor: $name - $yearsOfExperience will be there at 17:30")
      LocalDateTime.of(2019, Month.APRIL, 4, 17, 30)
}
```

Note this is an instance of subtyping polymorphism, in which we are working with a function that only cares about the trait `Attendee`. Pattern matching facilitates implementing these kind of functions, reducing repetition across your codebase.

{:prompt:} Pattern matcher syntax can be very powerful. You can for example match by extact `String` in a field. Modify the function above so it prints a custom message and time when you are the `Attendee` provided to as parameter. You can use `Essential Scala` chapter `3.5` for support on syntax.

{:teacher:} See chapter 3.5 of `Essential Scala` for guidance

## Notes {:teacher:}

Students with prior OO experience will undestand subtyping but may try to overuse it ala OO. Ensure they stay within ADT realm, not creating complex hierarchies of objects.

Parametric polymorphism is something so common in Scala (for example, `List[A]`) that they should try to understand it and implement it in their projects. Implementing their own simple `List[A]` can be a good exercise to make sure they understand both ADT and polymorphism correctly.

Modifying their project and implementing functions that use pattern matching will be a good way for students to understand the concepts. Try to focus on working closely with their codebase and find areas where rewriting code as polymorphic would reduce the amount of code written.

## Extensions {:teacher:}

For students that finish the above, focus on project work. Modelling logic as ADT and creating the functions to work with the model may create a loop in which the student discovers, while implementing a function, that the ADT could be refined to something better. This cycle will help students to better understand the concepts and to develop good foundations for the following lessons, so focus on that.

## Project work

You have modelled your data using ADT. Now it's time to start implementing the logic that works with that ADT. Define functions that provide the business logic of your project. Ensure they take advantage of the tools we practiced this lesson, like *pattern matching* and *polymorphism*.

{:teacher:} Discuss with students the main behaviour of their business api and which functions would implement it. Guide them towards polymorphic functions. 

