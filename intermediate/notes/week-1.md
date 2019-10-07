# Notes for Intermediate Week 1 Questions

1. This questions ensures they have basic concepts correct. They'll need these concepts to scafold the rest of the concepts we'll learn.

- An expression is program text that evaluates to a value. E.g. `1 + 1`
- A value is something stored in the computers memory that a program can manipulate. E.g. an integer stored as a 32-bit twos-complement value.
- All values are objects (and hence all objects are values). An object has methods that allow us to interact with the object. E.g. `1`
- A literal is a type of expression that evaluates to "itself". E.g. `1` or `"Hi"`
- A method belongs to an object and allows to interact with that object. A method may take parameters that allow us to customise how the method works. E.g. the `+` method on `Int`


2. Operator notation is kinda dumb and kinda great, but always confusing at first. Let's clear it up early.

`a.method(b) == a method b`


3. Practice writing some code.

```scala
object ScalaBridge {
  val theAnswer = 42
  def hello(name: String, answer: Int): String = 
    s"Hello ${name}, the answer is ${answer.toString}"
}
```


4. The point here is to emphasise that types are compile-time constructs. Developers who have worked with "dynamically typed" languages will often have a notion of types that corresponds to what we call runtime tags. All these expressions have types though the last one does not evaluate to a value.

A type is a set of values (or, equivalently, a constraint on values). All have type `Int`. You can solve this by reasoning from the type signatures of the `/` method on `Int` or experimentally by using the `console`.


5. The questions correspond to three different ways of acquiring knowledge:

- Appeal to authority. Ask a trusted expert, such as a teacher
- Reason from first principles. Mathematics.
- Observe the world. Science.

All three are needed in programming.


6. Answering question 4 could be done using any strategy. E.g. the students could reason from first-principles: types exist at compile-time, the literals have type `Int` and the operation has type `Int`, therefore the entire expression has type `Int`. Or they could observe the world: using the console they could type `:type 1/0`. Or they could ask someone else!


7. An expression is a part of a program that evaluates to a value. Expressions are important as they allow us to produce values, and transform values to other values.

A declaration usually gives a name to a value (and depending exactly on how we define these terms, a declaration may also be used for things like `import`, which don't compute anything.)

We need expressions we can compute things. We need declarations we don't have to continously repeat ourselves. Declarations allow us to give names to concepts and then use those names in other places.


8. `if` returns a value, so we can compose code more easily. This is a subtle point. Prepping their mind at this point.


9. Checking they understand scope.

At `<A>` the following are in scope: `AnExample`, `SomethingElse`, everything imported from `doodle.image`, and `image`
At `<B>` the following are in scope: `SomethingElse`, `AnExample`, everything imported from `doodle.image`, and `ziggy`

You can reason this one out, or enter the code and play around to see what names you can refer to.


10. Bringing up the concept of effects. Operations that return `Unit` has some observable effect on the world. `1 + 2` has no observable effect. We haven't formalised this yet. Just trying to plant the idea as preparation for the next section.

`Image.circle(100)` produces an `Image`, while calling draw has result `Unit`. `4 + 2` has type `Int` while `println` has type `Unit`. Methods that have some externally visible effect usually have type `Unit`, while those that transform some values into a different value do not.


11. There are at least two ways to answer this:
 
- a program might ask for input from the user, and we can't know what that input will be ahead of time and thus we cannot check it as compile-time;
- fundamental limits (Turing's halting problem, Godel's incompleteness theorem) show that in general we cannot compute all possible properties of a program. However we can make progress on specific examples!

I wouldn't expect students to know about the latter. It might be interesting to them, though.

