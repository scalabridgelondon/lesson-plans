// give the user a nice default project!
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "org.creativescala",
      scalaVersion := "2.12.8"
    )),
    name := "language",
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
    // For hedgehog
    resolvers += Resolver.url("bintray-scala-hedgehog",
                              url("https://dl.bintray.com/hedgehogqa/scala-hedgehog")
    )(Resolver.ivyStylePatterns),
    testFrameworks += TestFramework("hedgehog.sbt.Framework"),
    libraryDependencies ++= Seq(
      Dependencies.catsCore,
      Dependencies.catsEffect,
      Dependencies.fastParse,
      Dependencies.miniTest,
      Dependencies.miniTestLaws,
      Dependencies.hedgehog,
      Dependencies.hedgehogRun,
      Dependencies.hedgehogSbt
    )
  )
