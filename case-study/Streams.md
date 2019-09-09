# Streaming project

Some guidelines for the project, so we have a tempalte to follow and we know if we are achieving our aims.

## What are Streams

A stream processing system processes a possibly unbounded amount of data that arrives over time. 

Note that the data is *unbounded* which means we can potentially have an infinite `Stream` of data. As a result we want to process elements in the stream as they arrive, without buffering in memory.

We also want to separate describing what will happen from actually making it happen. To achieve this, we may want to represent effects (actions in our Stream) as types.

This exposes us to the concept of interpreters, a pattern commonly used when working with effects in functional programming. Interpreters take a description of a program (description achieved via types) and perform the steps these types describe

## Project core

This section lists the things we want to, ideally, achieve in this course.

The main aim is to implement a `Stream`. This stream will serve data, any kind of data, which means it will use parametric polymorphism. To achieve this end, we propose attacking the problem with a series of steps

There is a *Solutions* section at the bottom that proposes some possible answers for each section. 

## Define an API for the Stream

First of all we want to define an API for the stream. You can use the api of `List[A]` as a guide. Think about the main methods you may want to have available in your Stream, like `map`. 

Think also about methods to create a new Stream. How to create an empty stream, or one with a single element. Knowing Streams can be infinite, we may also want a way to do that (hint: check for `Iterator` class)

## Reification of the methods

Reification means turning all our methods (including their parameters) into data. For example, given this method:

```scala
def doSomething(a: Int, b: Int): Stream[A] = ???
```

we can reify it as follows:

```scala
final case class DoSomething(a: Int, b: Int) extends Stream[A]

def doSomething(a: Int, b: Int): Stream[A] = DoSomething(a, b)
```

In this step we are creating an ADT that represents our actions. This allows us to achieve the split between describing what we want to do and executing that series of instructions.

Once implemented, try to build a Streama long a series of operations on it, and print the result to see the separation of concerns in action. 

## Implement the interpreter and run the program

The last step is to implement the interpreter and execute some sample streams to verify it works as expected.

And interpreter is something as simple as a function that given a Stream iterates over the types that define actions and does something at each step. Something like:

```scala
def run[B](stream: Stream[A], zero: B)(f: (B, A) => B): B = ???

// simplified if you implement it in the Stream trait
def run[B](zero: B)(f: (B, A) => B): B = ???
```

The `f` in run is so we do something with the data, otherwise we would only iterate over it. Hint: you may want to use pattern matching and some helper method for this one.


## Next steps

??? Should we provide a template sbt project for these?

Once the main task is completed, we would like you to get exposed to some libraries in the Scala world, specifically the [Typelevel](https://typelevel.org) ecosystem. The following exercises may expose you to concepts we have not yet covered in the course, but at the end of the course you will have all the information needed ot understand class/method signatures.

That said, fully understanding is not a requisite to use something, so try to create something that builds and we will fill the knowledge gaps later on.

### Testing your stream

In software development it is a good practice to test our code. We have implemented our Stream, but we (probably) are missing some tests, which will make future changes easier.

We recommend you using [ScalaTest](http://www.scalatest.org) for testing purposes. Create a few tests for your Stream. Include a test that verifies that given an `Iterator` with over 10 million elements, we can still process the stream and not fail due to memory constraints.

### Real-life streams

The streams you will use at work will be provided by libraries which have had a lot of work done to ensure fast performance and reliability.

A good library to familiarise with this is [fs2](https://fs2.io) which is the foundation of other libraries in the Typelevel ecosystem.

We recommend to go over the [official guide](https://fs2.io/guide.html) as it has some *exercises* on it, and it will give you a taste of how it works and what can be achieved with it.

Once done, reimplement the logic of your application using `fs2`.

### Your Stream as a Service

[Http4s](https://http4s.org) is an http server fromt he Typelevel ecosystem. It uses `fs2` behind the scenes to implement the server. It also provides a client that you can use to connect to any http server.

Your task is to make your `Stream` available in some API endpoint, returning the stream as a stream. There is documentation on `Streaming` in the http4s website. Create a program that connects to that endpoint using the http4s client

You can use your own implementation, but it may be easier to wire things if you completed the previous point and converted the code to `fs2`.


## Solutions

Solutions are not the *only-and-true* solution but standard proposals that list things we would like to appear in the solution. Your particular solution may differ. Just make sure you understand what is different, and if there is any trade-off involved. 

## Understanding Interpreters

## Define an API for the stream

You can have an interface similar to:

```scala
trait Stream[A] {
  def map[B](f: A => B): Stream[B] =
    ???
    
  def flatMap[B](f: A => Stream[B]): Stream[B] =
    ???
}
object Stream {
  def pure[A]: Stream[A] = ???
  
  def const[A](a: A): Stream[A] =  ???
  
  def fromIterator[A](source: Iterator[A]): Stream[A] =
    ???
}
```

## Reify the methods

Example for some of the methods defined above. Note some case classes preserve the source, others don't:

```scala
sealed trait Stream[A] {
  import Stream._

  def map[B](f: A => B): Stream[B] =
    Map(this, f)

  def flatMap[B](f: A => Stream[B]): Stream[B] =
    FlatMap(this, f)    
}
object Stream {
  final case class Map[A,B](source: Stream[A], f: A => B) extends Stream[B]
  final case class FlatMap[A,B](source: Stream[A], f: A => Stream[B]) extends Stream[B]
  final case class Const[A](a: A) extends Stream[A]
  
  def const[A](a: A): Stream[A] = Const(a)
}
````

### Implement the interpreter and run the program

One possible solution:

```scala
sealed trait Stream[A] {
  import Stream._

  def map[B](f: A => B): Stream[B] =
    Map(this, f)
    
  def zip[B](that: Stream[B]): Stream[(A,B)] =
    Zip(this, that)
    
  def flatMap[B](f: A => Stream[B]): Stream[B] =
    FlatMap(this, f)
    
  def run[B](zero: B)(f: (B, A) => B): B = {
    def loop[C](stream: Stream[C])(zero: B)(f: (B, C) => B): B =
      stream match {
        case Map(s, f2) => loop(s)(zero)((b, d) => f(b, f2(d)))
        case FlatMap(s, f2) => loop(s)(zero)((b, d) => loop(f2(d))(zero)(f))
        case Case(a) => f(zero, a)
            
    loop(this)(zero)(f)
  }
}
```
