package modules

import scaldi.Module
import service.{MessageService, OfficialMessageService, SimpleMessageService}
import scaldi.play.condition._

class WebModule extends Module {
  bind [MessageService] when inProdMode to new OfficialMessageService
  bind [MessageService] when (inDevMode or inTestMode) to new SimpleMessageService
}