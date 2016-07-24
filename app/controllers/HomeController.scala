package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import scaldi.{Injectable, Injector, Module}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController(implicit inj: Injector) extends Controller with Injectable {
  val messageService = inject[MessageService]

  def index = Action {
    Ok(views.html.index(messageService.getGreetMessage("test user")))
  }
}

class WebModule extends Module {
  binding to new HomeController

  bind[MessageService] to new OfficialMessageService

}

trait MessageService {
  def getGreetMessage(str: String): String
}

class OfficialMessageService(implicit inj: Injector)
  extends MessageService with Injectable {

  val officialGreeting =
    inject[String](identified by "greeting.official")

  def getGreetMessage(name: String): String =
    s"$officialGreeting, $name!"
}

//class HomeController @Inject() extends Controller {
//
//  /**
//   * Create an Action to render an HTML page with a welcome message.
//   * The configuration in the `routes` file means that this method
//   * will be called when the application receives a `GET` request with
//   * a path of `/`.
//   */
//  def index = Action {
//    Ok(views.html.index("Your new application is ready."))
//  }
//
//}
