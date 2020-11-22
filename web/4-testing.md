# Testing

## Goals

- Learn the value of writing unit tests
- Learn how to test http4s endpoints
- Learn how to write tests using MUnit and ScalaCheck


## The Case for Testing

You probably had to restart the web server several times in the last section as you tested the end point you had written. This is tedious, and we there is no guarantee that what used to work will continue to work as we modify the code. Automated testing is better approach, where we write tests as programs. This means we can repeatedly run our tests, giving us confidence that we don't break existing features as we add new features.

The type of tests we'll write are called *unit tests*. These are tests for the individual components of our code. They are in contrast to *integration tests*, which test an entire system.


## Writing Tests

To write tests we need to understand:

- where we should place our tests so sbt can find and run them;
- how to write tests; and
- how to run tests from sbt.

Let's address each in turn.

By convention tests go under the directory `src/test/scala/`. The directory structure within that directory should mirror the structure in `src/main/scala`. So, for example, we would place a test for `src/main/scala/todone/ToDoneService.scala` in the directory `src/test/scala/todone/`. By convention we take the base name of the file we're testing and append `Suite`. So the tests for `ToDoneService.scala` go in the file `ToDoneServiceSuite.scala`. In the terminology that grown around testing a test case tests a particular condition and a test suite is a collection of test cases.

We will write our unit tests using two libraries: [MUnit][munit] and [ScalaCheck][scalacheck].


## Testinng http4s Routes

[munit]: https://scalameta.org/munit/
[scalacheck]: http://www.scalacheck.org/
