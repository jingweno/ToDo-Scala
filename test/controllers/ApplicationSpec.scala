import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.squeryl.PrimitiveTypeMode.inTransaction

import controllers.routes
import models.Database
import models.Task
import play.api.test.FakeApplication
import play.api.test.FakeRequest
import play.api.test.Helpers.OK
import play.api.test.Helpers.SEE_OTHER
import play.api.test.Helpers.contentAsString
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

  "A request to the apiTasks Action" should "respond with data" in {
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      inTransaction(Database.taskTable insert Task("foo"))

      val result = controllers.Application.apiTasks(FakeRequest())
      status(result) should equal (OK)
      contentAsString(result) should include ("foo")
    }
  }
}