# Type Classes

## Overview {:teacher:}

Core concept: "ad-hoc" polymorphism
Scala features:
- implicit parameters
- implicit values
- implicit classes

Refer to chapter 7 in `Essential Scala` book for additional detail.

## Introduction {:present:}

Before describing the concept let's talk about the problem we are trying to solve.
[Expression problem](https://en.wikipedia.org/wiki/Expression_problem) defined as:

> The goal is to define a datatype by cases, where one can add new cases to the datatype 
> and new functions over the datatype, without recompiling existing code, and while
> retaining static type safety (e.g., no casts).

We are talking about extending existing code across two dimensions: either we add a new class to the mix, or we add a new operation. In either case, we do not want to modify existing code.

If we use OO programming approaches, via inheritance, adding a new case will not require us to modify existing code. But adding a new method requires us to modify the parent trait at least, and possibly all children.

If we use an FP approach, a new method is a new function and doesn't require any change to the ADT itself. But adding a new case to the ADT will require us changing functions that pattern match over the ADT.


{:prompt:} Create some code that showcases the expression problem issues using an OO approach and a FP approach. 

{:teacher:} This can be based on data structures they have in their personal project, but try to choose a simple one to avoid getting lost in the noise. If they used a more OO approach take the chance to show them how it would be implemented in FP using ADT and functions. Same in reverse scenario.


## Type classes

Type classes are a solution (there are others) for the Expression problem. A type class has four components:

- the type class definition
- type class instances
- interfaces using implicit parameters
- interfaces using implicit parameters and enrichment

{:teacher:} the book (Essential Scala) introduces type classes by starting with implicits. It is a better way to build up the knowledge, so recommended to do it this way

### Implicits

Implicits (implicit values) are a convenience way to pass a type class instance to a method at compile time. By now we will assume we have a type class instance, we will construct them later on.

Using the same `Ordering` example as in the book:

```scala
import scala.math.Ordering

val minOrder = Ordering.forLessThan[Int]( _ < _ )
List(3, 4, 2).sorted(minOrder)

implicit val implicitMinOrder = Ordering.forLessThan[Int]( _ < _ )
List(3, 4, 2).sorted
```

We can see `sorted` is taking advantage of the implicit.


{:teacher:} See `notes` section, regarding implicits

{:prompt:} Remove the implicit from the context in the example to see how it fails at compile time, and which kind of message you will see when that happens. Create a second implicit of the same type, to again force a compile time error.

{:prompt:} Check the definition of the `sorted` method. Where is the implicit required? 

{:prompt:} Create a new function (in an object) that requires an implicit instance of `Ordering` and use it to sort some data.

{:prompt:} Try to take advantage of implicit contexts by defining an implicit for `Ordering` in a companion object so you can use it in another object without having to import it.


### Type class definition

A type class is similar to a trait, the difference comes when we create the instances.

```scala
trait AsString[A] {
  def asString(in: A): String
}

object IntAsString extends AsString[Int] {
  def asString(in: Int): String = in.toString
}

final case class MyClass(data: String, another: String)

object MyClassAsString extends AsString[MyClass] {
  def asString(in: MyClass): String = s"${in.data} - ${in.another}"
}

```

{:prompt:} Add a couple more instances for this type class. Use an ADT for one of them.

{:teacher:} Highlight why we use `A` type, by showing how if we don't have it in the trait adding new instances becomes a problem and we can't solve the expression problem properly. See section 7.3.

### Type class instances

As mentioned at the start we want to provide the type class instances as implicits, to reduce repetition. We usually add implicits in the companion object:


```scala
object AsString {

  implicit object IntAsString extends AsString[Int] {
    def asString(in: Int): String = in.toString
  }
  
}
```

{:teacher:} Remind the student about implicit contexts at this stage

{:prompt:} Transform all the type class instances from the previous section into implicit instances. 

### All together

We can now create a method that takes the type class as an implicit parameter and uses it to transform data to `String`

```scala
object StringUtils {

 def string[A](in: A)(implicit instance: AsString[A]): String =
 	instance.asString(in)
}

StringUtils.string(10)

val myClass = MyClass("data", "other")
StringUtils.string(myClass)
```

{:teacher:} Highlight again how would we need to implement this if we didn't have the type class. Use as example the new instances the student created for previous sections.

{:prompt:} Use this `StringUtils` method with the new type class instances created in previous sections. Create a new method that takes two inputs of the same type `A` and concatenates both strings as the output.


### Enrichment classes

Enrichment classes allow us to create interfaces that act as if they were methods native to our type.

```scala
// countVowels() is not a method from String in the standard library of Scala
"some string".countVowels() 
```

We achieve that by using implicit classes:

```scala
implicit class EnrichedString(str: String) {
   def countVowels(): Int = ???
}
```

{:teacher:} Talk about common enrichment classes, like [RichInt](https://www.scala-lang.org/api/current/scala/runtime/RichInt.html) and how they simplify the language. Talk about your own use cases of enrichment classes.

{:prompt:} Create an enrichment class for `Int`, adding a new method `factors` that returns a `List[Int]` of the factors of the number. See [this page](https://www.programiz.com/java-programming/examples/factors-number) for some sample code implemented in Java you can use for the logic of deciding if a number is a factor or not.


Enrichment classes can be combined with type classes. For example, see this implementation from the book:

```scala
implicit class HtmlOps[T](data: T) {
  def toHtml(implicit writer: HtmlWriter[T]) =
    writer.toHtml(data)
}
```

Here we have an implicit class that provides a `toHtml` method to any `T`, but it also requires a type class instance to be available as implicit or it will fail at compile time.

{:prompt:} Create an enrichment class so you don't need to call `StringUtils` explicitly.

{:teacher:} Once the exercise is complete, this can be a good moment to talk about context bounds. See section 7.7.


## Notes {:teacher:}

It should be explained how implicits can be dangerous if abused, as that is a common novice mistake:

- implicits that break type safety but turning a type into another magically
- implicits that are not explicit in the imports and can cause unexpected behaviour or compile time errors

It should also be explained why do we usually store implicits in the companion object, and the implicit scopes (see section 7.2 from the book). No need to describe priority rules in full, just to mention this may be relevant when creating libraries.


## Extensions {:teacher:}

### Lawful vs Lawless Type Classes

Some type classes follow some laws. This may be a good moment to start introducing the concept of lawful type classes, as they can be explained without using Higher Kinded Types.

Start with a simple one like `Semigroup`. The cats description of this type class can be helpful guiding this discussion: [https://typelevel.org/cats/typeclasses/semigroup.html]()

### Higher Kinded Types (HKT)

A type class can take not only a single type as a paramater, but also a higher kinded type. This is a very powerful mechanism that opens the door to more abstract code. Given this is an advanced topic, mentioning it and showcasing some examples to explain why is important to understand type classes may  at this stage.

Resources:

- [https://www.atlassian.com/blog/archives/scala-types-of-a-higher-kind]()
- [https://typelevel.org/blog/2016/08/21/hkts-moving-forward.html]()
- [https://en.wikipedia.org/wiki/Kind_(type_theory)]()

### Tagless final

Tagless final is another solution to the expression problem and a good way of organising a codebase. It is also a pattern used in libraries like `http4s` so exposure to this pattern at this later stage in the course could be beneficial for those students experimenting with Typelevel libraries.

Unfortunately it requires HKT, so only for those students whom are pushing boundaries and need more advanced content.


Example:

```scala
trait Store[F[_]] {
  def get(key: String): F[Option[String]]
  def put(key: String, value: String): F[Unit]
}

object Store {
  def apply[F[_]](implicit instance: Store[F[_]]): Store[F[_]] = instance
  
  implicit val newIOStore: Store[IO] = new DefaultStore[IO]
}

class DefaultStore[F[_]] extends Store[F[_]] {
  override def get(key: String): F[Option[String]] = ???
  override def put(key: String, value: String): F[Unit] = ???
}
```

Additional resources to introduce the pattern and discuss on it:

- [https://www.basementcrowd.com/2019/01/17/an-introduction-to-tagless-final-in-scala/]()
- [https://blog.scalac.io/exploring-tagless-final.html]()
- [https://underscore.io/blog/posts/2017/06/02/uniting-church-and-state.html]()

### Project Work

Add type classes instances to their project. A common one is `Show` for turning data to `String` format. Even if they use `cats` it would be a good exercise to implement it themselves as practice. They can then replace with the `cats` one if they want.

Discuss with students if there are places in their codebase that could benefit from using a type class. Likely candidates are functions that are pattern matching over ADTs. 

Once the concept of type class is understood, and if the student has started looking at lawful type classes, we may want to use some of them to simplify the code. A good candidate is usually `Monoid` if there are any folds in the code, so the student can see how we can simplify most of them by using this type class.

### More Examples 

`Show` is another useful and common type class, used to obtain `String` values representing your data. Implement it and compare it with the Cats implementation, to get exposure to type classes in libraries.

{:teacher:} Comparing the code will raise many questions on why some things are implemented in a  certain way in the Cats version.

Use the `Json` example of the book, see section 7.9. Copy it and then add instances for the ADTs the student created during the lesson.

More advanced examples can be implementing some common type classes, like `Semigroup` or `Monoid`. Use Cats documentation for guidance: [https://typelevel.org/cats/typeclasses.html]()

If the student has learned about HKT, implementing `Functor` may be a good exercise to understand the similarities between `List`, `Option`, and why HKT matter. Showcase code duplication removal.