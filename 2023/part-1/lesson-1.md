# Part One: Lesson One

## Goals

The goals of this lesson are to 

1. make sure everyone has Scala and a development environment installed and ready to go;
2. get a quick win by making something fun; and
3. start developing the foundational concepts so students can program effectively (more on this below).


## General Notes

[Creative Scala][creative-scala] is the text we're using. [Part One][part-one] corresponds to part one of the ScalaBridge curriculum

The first session is usually quite chaotic because everyone is getting used to the software and to each other. Don't expect much progress. This, coupled with the fairly dry material at the start of Creative Scala, means I suggest taking a slightly different course than just starting Creative Scala straight away.

There is lots of learn about programming. The mechanics of the language is what we usually think of when we think of learning to program, but process is just as important. Process includes using editors effectively, understanding compiler messages, and general problem solving strategies. If you're a mentor, try to point these out to the students when they naturally come up.

For mentors, your role is mostly about guiding the students through the material. This means solving problems with their setup, reminding them of the concepts they're learning when they're stuck and so on. You're not expected to directly lecture at them. They can read material in Creative Scala for that content. 

For students, there is no requirement that you do anything in particular. ScalaBridge London is much more self-directed than most other learning venues. If you want to explore something that's outside the curriculum, go faster, or go slower, that's entirely fine. You just need to communicate so that everyone has a good outcome. See the [Students][students] section of the website for more.

There will be lots of little technical problems along the way. This is expected. It might be best for a mentor and student to move to a separate voice channel to work through these without derailing the majority of the group.


## Activities

### Introductions

This activity starts with everyone in a voice channel in Discord. Your group should consist of about 4-6 students and 2-3 mentors. Your group should have a name. Create a text channel corresponding to this name, as there are some things that are easier to send via text.

You need a leader. It can be anyone; not necessarily a mentor. Anyone who wants to do it is fine.

Leader starts introductions and calls on each person to introduce themselves. Keep this relatively brief. The most important information is:

- name;
- previous programming experience;
- goal for attending ScalaBridge; and
- the geographical area they're in (some people will be online only and we'll probably try to get similarly located people together.)


### The Unbearable Burden of Setup

Next job is to make sure everyone is setup and can run the example project. It's probably easiest to get a show of hands in the text channel for those who are and aren't setup. Anyone who has problems should either be helped by the mentors or sent to the setup channel, depending on how far along they are.


### Fun Time!

Now everyone is setup (or away, getting setup) let's shift to something fun. The Creative Scala template has some commented out code. Uncomment it and see what happens. This might be a good time for a mentor to share screen and show a few process tips (how to open a terminal within Visual Studio Code, running sbt in the terminal, (un)commenting code in VS Code using `Ctrl+/`.)

Once everyone has done this on their own computer, try a little bit of mob programming. One person, perhaps the leader, can start a [Live Share][live-share] session and everyone else can join. (This will cause problems. Best to get experienced at fixing these now.) Now someone should come up with an idea to change the animation. Maybe change the color, or change the speed of the animation. Experiment and have fun! Whoever is running the share will have to show the animation on Discord screen share, as VS Code will, unfortunately, not capture it.

Do this for until it seems the majority are ready to move on.


### Creative Scala Time!

Now that people have a little bit of experience programming, the natural question will be "how does this all work?" It's time to get into Creative Scala. Read through "Expressions, Values, and Types" and then come together as a group to discuss the questions. Mentors should guide this discussion. Continue through the chapters as time allows. There are other lesson plans that cover this:

* [week-1.md](https://github.com/scalabridgelondon/lesson-plans/blob/master/beginner/questions/week-1.md) Notes on these questions [here](https://github.com/scalabridgelondon/lesson-plans/blob/master/beginner/notes/week-1.md).
* [another week-1.md](https://github.com/scalabridgelondon/lesson-plans/blob/master/intermediate/questions/week-1.md). Notes are [here](https://github.com/scalabridgelondon/lesson-plans/blob/master/intermediate/notes/week-1.md).
* [yet more lesson plans](https://github.com/scalabridgelondon/lesson-plans/blob/master/level1/expressions-types-values.md).


### Reflection

About 15 minutes before the session ends the leader should stop everyone and do some reflection:

* What have we learned? Recap the main points to reinforce learning.
* Does anyone feel they're in the wrong group (e.g. maybe they have much more experience than the other students)? If so, this needs to be communicated to the wider groups so groups can be shuffled.
* Is there anything else we can improve for next time? For example, the group may choose to do some reading before the next session. This may need to be communicated to the wider group depending on what it is.

Ambitious groups will record their learnings and reflections in a shared document, perhaps on Github.

[creative-scala]: https://www.creativescala.org/creative-scala/
[part-one]: https://www.creativescala.org/creative-scala/part-one-introduction.html
[students]: https://www.scalabridgelondon.org/students/
[vs-code-shortcuts]: https://code.visualstudio.com/shortcuts/keyboard-shortcuts-windows.pdf
[live-share]: https://code.visualstudio.com/learn/collaboration/live-share
