import java.text.AttributedString

name := "integration"

version := "0.7-SNAPSHOT"

publishMavenStyle := true

resolvers += Resolver.mavenLocal

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/comoctoreactive-1015"

//resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

// for debugging sbt problems
//logLevel := Level.Debug

libraryDependencies ++= Seq(
  "com.octo.reactive.audit" % "reactive-audit-lib" % "0.7",
  "com.octo.reactive.audit" % "reactive-audit-agent" % "0.7" % "test",
  "org.aspectj" % "aspectjweaver" % "1.8.2" % "test")

lazy val weaver = taskKey[String]("The aspectjwearver jar file.")
lazy val auditLib = taskKey[File]("The audit-reactive-lib jar file.")
//lazy val auditAgent = taskKey[String]("The audit-reactive-agent jar file.")
lazy val audit= taskKey[Unit]("Audit with reactive-audit.")
lazy val prepareAudit= taskKey[Unit]("Audit with reactive-audit.")

weaver     := ((fullClasspath in Test value) filter (_.data.getName.startsWith("aspectjweaver"))).head.data.getAbsolutePath

auditLib   := ((fullClasspath in Runtime value) filter (_.data.getName.startsWith("reactive-audit-lib"))).head.data
//auditAgent := ((fullClasspath in Test value) filter (_.data.getName.startsWith("reactive-audit-agent"))).head.data.getAbsolutePath


javaOptions in audit += "-javaagent:"+weaver.value

//mainClass  := Some("com.octo.reactive.sample.TestApp")

fork in audit := true


//def makeSomeResources(base: File): Seq[File] {
//  Seq.empty
//}
prepareAudit := {
  println("PREPARE AUDIT")
  val auditAgent = ((fullClasspath in Test value) filter (_.data.getName.startsWith("reactive-audit-agent"))).head.data
  val targetAgent= target.value / "reactive-audit-libs" / "reactivte-audit-agent.jar"
  val auditLib   = ((fullClasspath in Runtime value) filter (_.data.getName.startsWith("reactive-audit-lib"))).head.data
  val targetLib  = target.value / "reactive-audit-libs" / "reactivte-audit-lib.jar"
  IO.copyFile(auditLib,targetLib)
  IO.copyFile(auditAgent,targetAgent)
}

audit := {
  println("AUDIT")
  // Dependencies
  prepareAudit.value
  //(resourceGenerators in Test).value
  val result=(run in Compile).value
  println("RESULT="+result)
  val t=fullRunTask(audit, Runtime, "com.octo.reactive.sample.TestApp")
  println("t="+t)
//  val r = (runner in Compile).value
//  val input = name.value // or any other string setting(s)
//  val cp = (fullClasspath in Compile).value
  //toError(r.run("com.octo.reactive.sample.TestApp", data(cp), Seq(input), streams.value.log))
}

//resourceGenerators in Test += Def.task {
//  println("RESSOURCE GEN")
//  val file = (sourceManaged in Compile).value / "demo" / "Test.scala"
//  Seq(target.value / "reactive-audit-lib" / "reactivte-audit.jar")
//}.taskValue



//fullRunTask(audit, Runtime, "com.octo.reactive.sample.TestApp")


