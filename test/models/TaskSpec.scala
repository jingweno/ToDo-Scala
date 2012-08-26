import models.{ AppDB, Task }

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import org.squeryl.PrimitiveTypeMode.inTransaction

import play.api.test._
import play.api.test.Helpers._

class TaskSpec extends FlatSpec with ShouldMatchers {

  "A Task" should "be creatable" in {
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      inTransaction {
        val task = AppDB.taskTable insert Task("foo")
        task.id should not equal (0)
      }
    }
  }
}