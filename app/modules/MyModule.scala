package modules

import scaldi.Module
import service.{MessageService, OfficialMessageService}

class MyModule extends Module {
  bind [MessageService] when (inDevMode or inTestMode) to new OfficialMessageService()
  bind [MessageService] when inProdMode to new OfficialMessageService
}