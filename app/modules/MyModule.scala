package modules

import controllers.HomeController
import scaldi.Module
import services.{MessageService, OfficialMessageService}

class MyModule extends Module {
  binding to new HomeController

  bind[MessageService] to new OfficialMessageService

}