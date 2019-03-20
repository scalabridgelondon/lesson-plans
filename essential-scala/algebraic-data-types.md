# Algebraic Data Types

## Overview {:teacher:}

Core concept: modelling data using logical ands and ors
Scala features:
- sealed traits
- (final) case classes

Possibly touch on packaging (leaves inside a companion object)


## Introduction {:present:}

Modelling data using logical ands and ors.
Example:
- an attendee to ScalaBridge is a student OR a mentor (OR a host?)
- an attendee has a name AND age AND goal AND level of Scala experience AND ...

Terminology:
- this is an algebraic data type
- an OR is a sum type
- an AND is a product type

{:prompt:} Think of some data relevant to you that has the structure? It must have at least one AND and at least one OR.

{:teacher:} Can they perform the task? Can you learn something about their interests from what domain they choose?

How do we represent this in Scala code?

Two patterns:

1. `A` is a `B` or `C`

```scala
sealed trait A
final case class B() extends A
final case class C() extends A
```

2. `A` has a `B` and `C`

```scala
final case class A(b: B, c: C)
```

Show example:

```scala
// A ScalaBridge Attendee
sealed trait Attendee
final case class Student(name: String, goal: String) extends Attendee
final case class Mentor(name: String, yearsOfExperience: Int) extends Attendee
```

{:teacher:} Get students to create instances so you can see what their goals are and you can tell them a bit more about yourself.

{:prompt:} Create their data (from above) in Scala. Share solutions with class.


## Notes {:teacher:}

Some people will want to understand what a `case class` is etc. There are two ways of describing this:

- operational view, explaining it in terms of more primitive Scala features such as classes and companion objects;
- denotational view, explaining it in terms of "this is what you write when you're implementing an algebraic data type".

Different people will want different explanations. More experienced developers are likely to want the operational view. You can spend a lot of time on the operational view as you need to introduce a lot of Scala and JVM concepts (classes, companion object, apply, equals, toString, etc.) This is not necessarily a good use of time.


## Extensions {:teacher:}


### Packaging

How do we package ADTs in code? It's not a great idea to just leave the leaves of the ADT in the package as 1) it exposes implementation details that we may want to hide, 2) it brings a lot of things into the namespace when we import a package and 3) if we use type classes (e.g. Cats) we can run into issues around variance and type class instance selection (this is a point the students won't be able to appreciate yet). The convention with Scala (at least Typelevel code) is to write something like

```scala
sealed trait A
object A {
  final case class B() extends A
  final case class C() extends A
  
  def b(): A = B()
  def c(): C = C()
}
```

I.e. put the leaves into the companion object, and then create "smart constructors" in the companion object to construct instances. Note the smart constructors return the *base* type (`A`) not the subtype (`B` or `C`).


### Project Work

Discuss data structures that the students' project will require. How can these be modelled as algebraic data types?


### More Examples

An `IntList` is either
- a `Pair` containing a `head` that is an `Int` and a tail that is an `IntList` OR
- `Empty`

Implement this ADT.

You can also do binary trees, `Option` (for a fixed type, as we don't have generic types yet), etc.

Also fun (and possibly mind bending) are to implement grammars that describe DSLs. E.g. a grammar for creating sentences, or images, or whatever is of interest to the students. This will probably tie into their project work.

For example a very simple grammar for images (a simplified version of Doodle) could be, an `Image` is:

- a `Circle` with a radius;
- a `Rectangle` with a width and height;
- an `Image` `on` and `Image`;
- an `Image` `above` and `Image`; or
- an `Image` `beside` and `Image`.

An example is `Circle(10).beside(Circle(30))`.


### Names

Where do the names sum and product come from?

Consider a really simple sum type: `A` is a `B` or `C`

```scala
sealed trait A
final case class B() extends A
final case class C() extends A
```

How many possible values are there? The answer is two (`B` or `C`), which is one `B` + one `C`. The total number of possible values is the *sum* of the "leaves" within the OR.


Let's do the same for a product type. Let's say `A` has two `Booleans`:

```scala
final case class A(left: Boolean, right: Boolean)
```

Each `Boolean` has two values. The total number of values of type `A` is 2 * 2 = 4, hence a *product*.
