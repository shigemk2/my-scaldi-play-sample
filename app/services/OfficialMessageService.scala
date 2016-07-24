package services

import scaldi.{Injectable, Injector}

class OfficialMessageService(implicit inj: Injector)
  extends MessageService with Injectable {

  val officialGreeting =
    inject[String](identified by "greeting.official")

  def getGreetMessage(name: String): String =
    s"$officialGreeting, $name!"
}
