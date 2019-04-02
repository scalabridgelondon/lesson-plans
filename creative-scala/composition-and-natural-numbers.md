# Composition and Natural Numbers

I set this homework:

The mission is to design a geometric pattern like you might find on 1960s fabric. Come with a sketch, image,  or whatever works for you. Keep it simple enough that you can realise it in code. Bonus points if you can get Doodle to draw it, but that’s not necessary at this stage.

I generated a simple example from the following code:

```scala
import doodle.core._
import doodle.core.Image._
import doodle.core.transform.Transform
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

object Fabric {
  import PathElement._

  val baseUnit = 10

  val leaf = Image.closedPath(
    List(
      lineTo(-baseUnit, baseUnit),
      lineTo(0, 3 * baseUnit),
      lineTo(baseUnit, baseUnit),
      lineTo(0, 0)
    )
  )

  val background = Image.square(4 * baseUnit).at(0, 1.5 * baseUnit).noLine.fillColor(Color.darkGray)

  val unit = (leaf.fillColor(Color.turquoise).noLine.on(background)).beside(
    leaf.fillColor(Color.turquoise.spin(180.degrees)).noLine.on(background)
  ).at(0, -1.5 * baseUnit) // Reposition so the centre of the square is the center

  val section = (unit.beside(unit.rotate(180.degrees)))
    .above(unit.transform(Transform.horizontalReflection).beside(unit))

  val chunk = (section.beside(section)).above(section.beside(section))

  val image = (chunk.beside(chunk)).above(chunk.beside(chunk))
}
```

A few pointers: missoni and houndstooth are classic patterns. The V&A has a lot of resources (e.g. https://www.vandaimages.com/preview.asp?image=2006AT3446-01&wwwflag=1&imagepos=7 and https://www.vandaimages.com/preview.asp?image=2011EP9040&itemw=4&itemf=0002&itemstep=1&itemx=10  These would probably be too hard to recreate, but they give some ideas.)


At this session there are a lot of things to address:

* Scala syntax. Students have written some Scala code but don't really understand the syntax yet.
* Scala semantics. Need to actually understand how program evaluation works.
* Computational thinking. Understand how to break a problem down into a bunch of steps.
* Structural recursion over the natural numbers. Builds things where the size of thing we've building is a function of the natural numbers.

Start with the computation thinking. Look at the student's fabric designs. Can we break them down into smaller components? For example my pattern above has a basic element (the "leaf" on a square) and various transformations to create the final image. We could make it more interesting by adding other transformation. E.g. break up the strong vertical lines by adding some more rotations. Think of theme and variations. Same thing applies to music (e.g. verse/chorus structure in popular music; more exotic is Aphex Twin's aisatsana [102] which is based on a variations of a few basic phrases).

Ok, now we understand how to build the fabrics, implement them. Maybe in groups. This is a good chance to introduce Scala syntax and semantics, bits of Doodle, and to make lots of mistakes and learn from them. Also abstraction using names (`val`) as in the example above.

Next step is to talk about generating repeats of the basic shapes. What if we wanted two repeats of the pattern? What about ten? It gets tedious to write these out by hand. We want to abstract over a process here. We can't do this with just a name as we've used before. Introduce structural recursion over the natural numbers. Use Lego to explain, if you remembered to bring it.

If time look at examples in Creative Scala of building fractals using structural recursion over the natural numbers.

What interesting things can we do that use this new tool? What about recursions where it's not the size but, say, the colour or the shape we adjust?
