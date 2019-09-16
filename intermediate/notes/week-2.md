# Notes for Intermediate Week 2 Questions

1. This questions ensures they have basic concepts correct. 

2. Practice basic syntax and reinforce basic concepts. Make them think a bit about how you would want to represent a pet. E.g. if it's a vet what information would they be interested in?

3. You can reason this out or do it experimentally. The answer is `Nothing`. Exceptions produce no value and `Nothing` is the sub-type of all other types, so exceptions can be thrown in any place where another type is expected.

4. Whenever data is immutable case classes as appropriate. We're biased towards immutability in Scala, so almost all situations. 

5. The core of the task here is to extend an existing trait. The idea is have some output that is fun an motivates students to explore a bit further.

6. The core of the idea is to represent data that can be described using logical ands and logical ors. It's important to understand the concept exists outside of the implementation. The implementation in Scala consists of `sealed trait` (or `sealed abstract class`) and `final case class`.

7. Checking they can recognise an algebraic data type.

8. They haven't covered structural recursion yet, but they have the components to implement it this is hinting at what will come next and preparing them for it.

9. More algebraic data types, in a different context. Showing they can represent complex grammars.

10. Should be fairly straightforward.
