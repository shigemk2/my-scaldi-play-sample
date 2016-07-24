package service

import scaldi.{Injectable, Injector}

trait MessageService {
  def getGreetMessage(str: String): String
}

class OfficialMessageService(implicit inj: Injector) extends MessageService with Injectable {
  val officialGreeting = inject [String] (identified by "greeting.official")

  def getGreetMessage(name: String) = s"$officialGreeting, $name!"
}
