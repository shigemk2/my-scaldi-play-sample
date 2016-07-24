package modules

import scaldi.Module
import service.{MessageService, OfficialMessageService}

class MyModule extends Module {
  bind [MessageService] to new OfficialMessageService

  binding identifiedBy "greeting.official" to "Welcome"
}