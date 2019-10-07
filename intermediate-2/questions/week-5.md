## Week 5

1. Lets create an Algebraic Data Type for a Family Tree called `Tree`. 
    
    This is a very limited "backwards" family tree where the nodes are the parents of the node before it. 

    A `Tree` is a:
    * A `Leaf` with a String for a person's name
    * A `Node` with a String for a person's name and a left `Tree` and a right `Tree`
    
    Instantiate a family tree starting with yourself and going as far as you'd like.

2. Write a `size` method that finds the size of a given Tree. We've used this strategy a few times now.

3. Write a `prettyPrint` method for our family tree that returns a String of the whole tree. Feel free to get fancy with formatting!

4. Write a `contains` method that takes a name and returns a Boolean indicating if that name is in our tree or not.

5. Trees are a useful data structure in computer science. 
    We now want to use our tree to store numbers. 
    
    How can we change our Tree so all our methods work for an `Int` or any other type? Lets do it. 

    *Note*: Pay attention the contains method. Also do we still have good names for our parameters? 

6. Our solutions for `size`, `prettyPrint` and `contains` look very similar. How could we capture the differences in each one?

7. What is the syntax for declaring a function? What is a higher order function? 

8. The function structure we're repeating is usually called a `fold` in functional programming. 
    
    There is a general strategy for deriving this function for an Algebraic Data Type in Section 5.3.1 of Essential Scala. 
    Using this strategy, see if you can complete the rest of the fold function below.

    ```scala
    def fold[B](leafF: ? => B, nodeF: ? => B): B = {
      ???
    }
    ```

    Remember `fold` looks like our other methods in structure, and follow the types!

9. Now implement `size` using `fold` instead of repeating the pattern matching. 

    One approach is to first create the `leaf` and `node` functions based on your current `size` code, and then use `fold`.

10. Lets do it for `prettyPrint` and `contains` too.

11. How did you changed the way you thought about the problem when using fold? What logical abstractions where we thinking in?
Do we have a name for this? :)