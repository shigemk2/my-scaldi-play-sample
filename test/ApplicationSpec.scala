import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import scaldi.play.{ScaldiApplicationBuilder, ControllerInjector}
import scaldi.Module
import service.MessageService
import scaldi.play.condition._
import modules.WebModule

import play.api.cache.EhCacheModule
import play.api.inject.BuiltinModule

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  class TestModule extends Module {
    bind [MessageService] when (inDevMode or inTestMode) to new MessageService {
      override def getGreetMessage(name: String): String = "Test Message"
    }
  }
}
