# ScalaBridge London Lesson Plans

This repository contains lesson plans for [ScalaBridge London][scalabridge-london]. They are distributed under the terms of the accompanying license.


## Designing a Lesson Plan

A lesson plan has three main parts:

1. learning goals; 
2. defining content that achives the goals; and
2. creating learning activites to deliver the course content in a way that maximises learning.


### Learning Goals

Consider both practical and theoretical skills that students should acquire in a lesson. For example, a lesson aimed at beginners might ask them to:

- understand the substitution model of evaluation;
- apply substitution to Scala expressions; and
- write and run simple Scala expressions.

The substitution model of evaluation forms the theoretical core of the lesson, but actually writing and running Scala expressions brings in important practical aspects such as Scala syntax, debugging, and IDE usage.


### Lesson Content and Activities

The lesson content covers all the key points the student is expected learn from the lesson. The lesson activites determine how the students access the content. It is difficult to separate the two in practice---lesson activities drive how content is presented---so we discuss them together here.

ScalaBridge lessons should be easy to mentors to pick up, which requires fairly detailed descriptions of lesson activities. 


#### Flipped Classrooms

We suggest all ScalaBridge lessons are designed in a "flipped classroom" style. In this style students are expected to do preparatory work on their own time, and in sessions they engage in active work with the material they covered outside of the session. This useful for two reasons: it maximises the usefulness of the time spent with the mentors and other students, and it enables people to participate in ScalaBridge is more ways (e.g. they can work on their own if they so desire.)

The prepartory work is usually reading, but might involve watching a video. Writing text is vastly cheaper than creating video, so we tend to gravitate to that, but it has limitations. We encourage lesson authors to experiment with video if they have the time and equipment to do so.

When creating content we encourage:

- meta-learning

#### In Session Activities

Traditional in session activites for teaching programming revolve around individual programming. Research suggests we can significantly improve learning by using a variety of different activities. The [PRIMM model][primm] is a good approach for overall session design. PRIMM stands for:

- predict;
- run;
- investigate;
- modify;
- make.

Predict tasks force students to inspect their mental model of code to predict what will happen when it runs. Running confirms those predictions, or indicates the mental model needs to be revised. Investigate tasks ask students to further breakdown the properties of code (e.g. label different components, trace through code, etc.) Modify asks the students to modify existing code to achieve new functionality and make asks students to create completely new code. The PRIMM model gently bootstraps the student into creating new code and probes their mental model in many different ways.

[Use-Modify-Create][umc] is a similar but simpler model than PRIMM. I believe it is most appropriate for more advanced students who don't need to spend as much time on program semantics as PRIMM.

There are a few best practices we recommend for individual components of a session:

- Pair programming. Should be self explanatory
- Peer instruction. Is the CS education literature this often refers to a specific practice of asking students a question and then having them explain their answer to the person next to them. This works well in a large classroom. In ScalaBridge's smaller groups we don't have to restrict ourselves to this and can use the broader idea of students learning together. Pair programming is an example of peer learning in this broader sense. In general anything a student might do individually they can do with a peer.
- Connecting examples to relevant background. Don't assume students are interested in mathematics and hard science. Likely they are coming to ScalaBridge from a different background. Connecting examples to, say, graphic design, literature, or biology is likely to resonate more with students.

[scalabridge-london]: https://scalabridgelondon.org/
[primm]: https://blogs.kcl.ac.uk/cser/2017/09/01/primm-a-structured-approach-to-teaching-programming/
[umc]: https://users.soe.ucsc.edu/~linda/pubs/ACMInroads.pdf
