# Testing

## Goals

- Learn the value of writing unit tests
- Learn how to write unit tests using MUnit
- Learn how to test http4s endpoints


## The Case for Testing

You probably had to restart the web server several times in the last section as you tested the end point you had written. This is tedious, and we there is no guarantee that what used to work will continue to work as we modify the code. Automated testing is better approach, where we write tests as programs. This means we can repeatedly run our tests, giving us confidence that we don't break existing features as we add new features.

The type of tests we'll write are called *unit tests*. These are tests for the individual components of our code. They are in contrast to *integration tests*, which test an entire system.


## Writing Tests

To write tests we need to understand:

- where we should place our tests so sbt can find and run them;
- how to write tests; and
- how to run tests from sbt.

Let's address each in turn.

### Test File Structure

By convention tests go under the directory `src/test/scala/`. The directory structure within that directory should mirror the structure in `src/main/scala`. So, for example, we would place a test for `src/main/scala/todone/ToDoneService.scala` in the directory `src/test/scala/todone/`. By convention we take the base name of the file we're testing and append `Suite`. So the tests for `ToDoneService.scala` go in the file `ToDoneServiceSuite.scala`. In the terminology that grown around testing a *test case* tests a particular condition and a *test suite* is a collection of test cases.

### Writing Tests with MUnit

We will write our unit tests using [MUnit][munit]. To keep things as simple as possible we will learn about MUnit before we learn about testing http4s applications. 

Here's a test suite which contains one test case.

```scala
class PalindromeSuite extends munit.FunSuite {
  test("tacocat is a palindrome") {
    val theCat = "tacocat"
    assertEquals(theCat, theCat.reverse)
  }
}
```

Let's break it down.

The first line

```scala
class PalindromeSuite extends munit.FunSuite
```

declares the test suite.

The next line

```scala
  test("tacocat is a palindrome")
```

declares a test case within the test suite. The `String` name should describe the condtion we are checking. Within the body of the test case we write the code to check that condition. Here we are testing that "tacocat" is a palindrome, meaning it is the same backwards and forwards.

The check is done with the line

```scala
    assertEquals(theCat, theCat.reverse)
```

`assertEquals` is a method defined by MUnit. Should the condtion fail MUnit will generate useful information for us to help track down the problem. MUnit provides a number of [different assertions][munit-assertions] for different cases.

### Running Tests

To run tests we use the `test` command in sbt. Run that command now. You should see output indicating that the test we just created has passed.

It's useful to see what happens when a test case fails. Create a new test case that checks if "tabbycat" is a palindrome. As this is not the case this test case will fail. Run the `test` command to check that it does fail and see what the output looks like.


## Testing http4s Routes

A big part of testing our web application will be testing our routes. In http4s a route is essentially a function from a `Request` to an `IO[Option[Response]]`.

**To be completed**

[munit]: https://scalameta.org/munit/
[scalacheck]: http://www.scalacheck.org/
[munit-assertions]: https://scalameta.org/munit/docs/assertions.html
