# Web Service Architecture

## Goals

- Learn about MVC architecture
- Implement a simple MVC architecture
- Get a working `close` endpoint


## Handling State

In the last lesson we implemented a `close` method, but these changes don't show up in the user interface. If you look in the web developer console you will see that the user interface makes a call to the `tasks` endpoint immediately after calling `close`. However the response to the `tasks` call does not reflect the change we tried to make. Exactly the same tasks are returned whereas there should be a closed task in the result.

Things that change we call *mutable state*, or sometimes just state for short. State is problematic in a functional programming world, because it breaks substitution. If, say, the value bound to a name changes over time we cannot freely substitute the name for the value.

Later we'll see ways we can handle state while still keeping substitution. For now we'll just brush this under the table and freely use mutable state.


## Program Architecture

As programs get larger we need to add structure to keep them comprehensible. We call this structure *architecture*.

We're going to use an architecture known as *model-view-controller*, or *MVC* for short. In MVC we divide our program into three main components:

- the model, which holds state;
- the view, which is responsible for producing output and receiving input; and
- the controller, which mediates between the model and view.

In our case

- the model is the current tasks and any other state we think we need;
- the view is our routes; and
- the controller is the "business logic" that translates requests to endpoints into actions on the model, and prepares model data for endpoint responses.

Our initial MVC implementation will be very simple. So simple in fact that you might think why we bother. If we put structure in place now it will pay off in the long run as the project becomes more complex.


## Adding the MC

Let's start by adding a `Model` and a `Controller`. They can just be objects for now. You'll probably want to put them each in their own file.

In the model we will store the tasks. We'll want to be able to list tasks in the order in which they were created (so that the user interface displays them in a consistent order) and find a task by an `Id`. A `LinkedHashMap` is a good choice for this. It's in the `scala.concurrent.mutable` package.

Your task now is to fill out the model and controller:

1. Create a model that contains the tasks. 
2. Add a method to the controller that gets a `Tasks` from the data in the model.
3. Hook up the `tasks` endpoint in the `ToDoneService` (`Root / "api" / "tasks") so it calls the method you just created on the controller.

Once this is done you shoulhd have the application working as before but with the new architecture in place.

Now add a method to the controller to implement the `close` endpoint, and connect the endpoint to it. Note there is a `close` method on `Task` that you might want to use.

When this is all done you will hopefully have working functionality to mark a task as closed.

In these instructions we have deliberately gone light on detail. Part of the task here is to figure out the details yourself, make some mistakes, and learn from the experience. If you get stuck ask for help!
