import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import controllers.routes
import play.api.test.FakeApplication
import play.api.test.FakeRequest
import play.api.test.Helpers.SEE_OTHER
import play.api.test.Helpers.inMemoryDatabase
import play.api.test.Helpers.redirectLocation
import play.api.test.Helpers.running
import play.api.test.Helpers.status

class ApplicationSpec extends FlatSpec with ShouldMatchers {

  "A request to the newTask action" should "respond" in {
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val result = controllers.Application.newTask(FakeRequest().withFormUrlEncodedBody("label" -> "FooBar"))
      status(result) should equal(SEE_OTHER)
      redirectLocation(result) should equal(Some(routes.Application.tasks.url))
    }
  }
}