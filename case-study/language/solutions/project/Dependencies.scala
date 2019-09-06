import sbt._

object Dependencies {
  // Library Versions
  val catsVersion       = "1.6.1"
  val catsEffectVersion = "1.3.1"
  val fastParseVersion  = "2.1.3"
  val miniTestVersion   = "2.6.0"
  val hedgehogVersion   = "49859b13f023a70937c6e4ee9770fb84f63bdcc5" // dunno what a good version is

  // Libraries
  val catsCore     = "org.typelevel"  %% "cats-core"       % catsVersion
  val catsEffect   = "org.typelevel"  %% "cats-effect"     % catsEffectVersion
  val fastParse    = "com.lihaoyi"    %% "fastparse"       % fastParseVersion
  val miniTest     = "io.monix"       %% "minitest"        % miniTestVersion % "test"
  val miniTestLaws = "io.monix"       %% "minitest-laws"   % miniTestVersion % "test"
  val hedgehog     = "hedgehog"       %% "hedgehog-core"   % hedgehogVersion % "test"
  val hedgehogRun  = "hedgehog"       %% "hedgehog-runner" % hedgehogVersion % "test"
  val hedgehogSbt  = "hedgehog"       %% "hedgehog-sbt"    % hedgehogVersion % "test"
}
