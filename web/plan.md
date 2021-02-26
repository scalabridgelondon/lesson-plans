# Web Course Plan

## Goals

Overall this course represents a transition from programming fundamentals in earlier courses to domain specific knowledge that is useful for commercial development.

More specific goals of this course are:

- reinforce Scala concepts previously learned;
- introduce core concepts in web programming;
- introduce basic programming practices such as testing and tool usage;
- introduce basic concepts in code architecture.


## Structure

- [x] Setup
- [x] HTTP Methods
- [x] Services and Routing
- [x] MVC Architecture
- [x] Testing
- [ ] Testing Web Services
  - Learn how to test web routes, which requires a *very* basic overview of using IO. Construct a `Request` and check the contents of a `Response`.
- [ ] Adding More Routes
  - Add necessary routes to finish the API. Discover them by reading `Api.scala` on the frontend.

Optional extensions. This is not strictly necessary to complete the project but would further student knowledge.

- [ ] Add [embedded Postgres][embedded-postgres] to the project.
  - This gives the student some more experience using sbt
- [ ] Write a new backend that uses Postgres
  - This is quite a big task, which would need to be decomposed into smaller tasks (e.g. learning about SQL, implementing a query, implementing an update.) However some knowledge of SQL is important for commercial practice.
- [ ] Property based testing.
  - Introduce ScalaCheck as a way to write more comprehensive tests with less effort.


[embedded-postgres]: https://github.com/zonkyio/embedded-postgres
