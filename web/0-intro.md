# Introduction

## Goals

The goal of this section is to get the project setup and ready to start working on.


## Prerequisites

Some familiarity with Scala, and a willingness to learn new things where required.


## Setup

*Fork* the repository https://github.com/scalabridgelondon/todone/

- Why fork instead of clone? Because it allows you have put your changes in a place others can see them, while still being able to receive updates from this repository.

When the fork has been created, clone it to your computer.

Start `sbt` in the cloned directory. Within `sbt`:

- switch to the backend project: `project backend`;
- run the web server: `run`

When it finishes starting, visit `http://localhost:3000/` in your web browser and you should see the ToDone user interface.


## Frontend and Backends

In web developer speak, the *frontend* is the part that creates the user interface. These days this is usually displayed in a web browser or mobile application. The *backend* is the part that handles all the data that the application uses, and the actions the user performs on the data.

The frontend usually runs on the user's computer (e.g. in their web browser), while the backend runs on a computer the developer controls (a *server*).

In this example both the frontend and backend are written in Scala, but it is often the case that the frontend is written in Javascript. Javascript is the language that the web browser runs (though this is changing). Scala can compile into Javascript.


## Projects

sbt is controlled by the declarations in the file `build.sbt`. Take a look at this file to find the different projects that make up ToDone. Look for lines like this:

```scala
lazy val backend = project
  .in(file("backend"))
  ...
```

You should find three projects: the frontend, the backend, and a project that is shared between both.

We used the command `project backend` to switch to the backend project. We could have switched to the frontend or shared project using the same `project` command.



## Instructor Notes

Students may not be familiar with Github and Git. Pairing such students who those who are familiar may allow the lesson to progress faster than you doing all the support work. Learning Git and Github are some of the skills we want students to pick up, but we don't have time to teach everything that students need to learn (nor do I have the will to write material on everything!)
