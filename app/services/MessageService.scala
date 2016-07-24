package services

trait MessageService {
  def getGreetMessage(str: String): String
}