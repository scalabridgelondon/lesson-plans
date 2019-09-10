# Names

We have now created a very simple little language. It's time to make that language more expressive. We're going to do two things in this section: 

1. Add names (sometimes called bindings or variables) to our language, so we can give names to values and use these names in expressions.

2. Create a richer language, with more types and operations. This means we can write more interesting programs in our language. This won't require any new techniques but we'll introduce the possibility of more run-time and compile-time errors, which will require we restructure our interpreter to handle the additional complexity.


## Naming Values

Our main new feature will be the ability to give names to values, and use those names in expressions. Naming a value is what we do in Scala when we write `val`. In the programming theory literature this is sometimes called a binding, as we "bind" a name to a value. The practicing programmer sometimes calls these variables, though we will not allow our names to vary the value they refer to.

This will be quite a big change. We will need to:

- Add syntax to declare a name. This introduces a new type of program, a declaration, in contrast to the expressions we have been working with so far.

- Add syntax to use a name within an expression. This will require error checking (if a name has not been declared before use it is an error). This implicitly requires we define a notion of scope: where in a program is a name available to be used?

- At run-time we must create what is called an environment, which maps names to values, and when we encounter a name within an expession we must look it up in the environment.


### Declaring Names

We will start with the syntax to declare a name. We will use the following syntax, which is similar to the modern version of Javascript's syntax:

```scala
let <name> = <expr>
```

The word `let` is a keyword---a special word that can only be used by the programming language to indicate that a language feature is being used. `<name>` is the name we're declaring, and `<expr>` is the expression that will compute the value we're naming.

We will we allow for names? You can be as flexible as you like (so you could allow fancy emoji names if you wish), but you must ensure a name cannot be the same as a reserved word (of which `let` is our only one) and we cannot mistake it for a literal (so it cannot be a number).

We must also change what a program is. It is no longer sufficient for a program to be a single expression. A program is now a sequence of expressions or declarations. This entails a change to `eval`: what is the result of evaluating a program that can contain many expressions? We'll let you decide.

Make the above changes to your language. At this point the interpreter doesn't need to do anything when it reads a declaration as we don't have anyway of using names. It is sufficient to just parse them successfully and represent them in the abstract syntax tree.
