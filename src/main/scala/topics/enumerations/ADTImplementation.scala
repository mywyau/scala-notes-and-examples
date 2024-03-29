package topics.enumerations

class ADTImplementation {

  // Enumeration suffers from a major flaw: exhaustivity is not checked in pattern matches.
  // Well you can handle the other case for NotOk too then it will be fine tbh.

  object Status extends Enumeration {
    val OK, NotOk = Value
  }

  def foo(somethingToMatch: Status.Value): Unit = {
    somethingToMatch match {
      case Status.OK => println("ok")
      // needs a 'case _ => <do something>' - here then will be fine, but currently this would not give a warning according to article
    }
  }

  /*
    This compiles without a warning but will blow up at runtime:
      foo(Status.NotOk)   //   <----- calling NotOk kills it
  */

  // The below implementation is a alternative to the sealed trait version of sum types/enums
  sealed abstract class Status extends Product with Serializable

  object StatusADT {

    final case object Ok extends Status // <---- make case objects 'final' and put into own object is good practice allows import of object and renaming on import
    final case object NotOk extends Status

  }

  //The compiler now has enough information to check whether your pattern matches are exhaustive:

  def foo(w: Status): Unit = w match {
    case StatusADT.Ok => println("ok")
  }

  // warning: match may not be exhaustive.
  // It would fail on the following input: NotOk
  // def foo(w: Status): Unit = w match {    unfinished WIP
  //

}
