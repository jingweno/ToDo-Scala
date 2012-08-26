import controllers.routes
import models.{ AppDB, Task }

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import org.squeryl.PrimitiveTypeMode.inTransaction

import play.api.http.ContentTypes.JSON
import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends FlatSpec with ShouldMatchers {

  "A request to the newTask action" should "respond" in {
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val result = controllers.Application.newTask(FakeRequest().withFormUrlEncodedBody("label" -> "FooBar"))
      status(result) should equal(SEE_OTHER)
      redirectLocation(result) should equal(Some(routes.Application.tasks.url))
    }
  }
}