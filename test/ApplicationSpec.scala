package test

import org.specs2.mutable._
import play.api.cache.EhCacheModule
import play.api.inject.BuiltinModule
import play.api.test._
import play.api.test.Helpers._
import scaldi.play.{ControllerInjector, ScaldiApplicationBuilder}
import scaldi.Module
import service.MessageService
import modules.WebModule
import scaldi.play.condition._
import ScaldiApplicationBuilder._

class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in {
      withScaldiApp() {
        status(route(FakeRequest(GET, "/boum")).get) must equalTo(NOT_FOUND)
      }
    }

    class TestModule extends Module {
      bind [MessageService] when (inDevMode or inTestMode) to new MessageService {
        def getGreetMessage(name: String) = "Test Message"
      }
    }

    "render the index page (with full control over the module composition)" in {
      val module = new TestModule :: new WebModule :: new ControllerInjector

      withScaldiApp(modules = Seq(module, new EhCacheModule, new BuiltinModule), loadModules = (_, _) => Seq.empty) {
        val home =  route(FakeRequest(GET, "/")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")

        contentAsString(home) must contain ("Test Message")
      }
    }

    "render the index page (with just override module)" in {
      withScaldiApp(modules = Seq(new TestModule)) {
        val home =  route(FakeRequest(GET, "/")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")

        contentAsString(home) must contain ("Test Message")
      }
    }

    "render the index page (with explicit `ScaldiApplicationBuilder` usage)" in {
      val application = new ScaldiApplicationBuilder().prependModule(new TestModule).build()

      running(application) {
        val home =  route(FakeRequest(GET, "/")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")

        contentAsString(home) must contain ("Test Message")
      }
    }
  }
}
