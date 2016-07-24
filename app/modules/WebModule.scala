package modules

import scaldi.Module
import service.{MessageService, OfficialMessageService}

class WebModule extends Module {
  bind [MessageService] to new OfficialMessageService

  binding identifiedBy "greeting.official" to "Welcome"
}