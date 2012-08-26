import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.squeryl.PrimitiveTypeMode.inTransaction

import models.Database
import models.Task
import play.api.test.FakeApplication
import play.api.test.Helpers.inMemoryDatabase
import play.api.test.Helpers.running

class TaskSpec extends FlatSpec with ShouldMatchers {

  "A Task" should "be creatable" in {
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      inTransaction {
        val task = Database.taskTable insert Task("foo")
        task.id should not equal (0)
      }
    }
  }
}