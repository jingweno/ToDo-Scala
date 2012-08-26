package models

import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode.__thisDsl
import org.squeryl.PrimitiveTypeMode.from
import org.squeryl.PrimitiveTypeMode.inTransaction
import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.PrimitiveTypeMode.select
import org.squeryl.Schema
import scala.collection.immutable.List

case class Task(label: String) extends KeyedEntity[Long] {
  val id: Long = 0
}

object AppDB extends Schema {
  var taskTable = table[Task]("task")
}

object Task {

  def all(): List[Task] = inTransaction {
    from(AppDB.taskTable)(taskTable =>
    select(taskTable)).toList
  }

  def create(label: String) = inTransaction {
    AppDB.taskTable.insert(Task(label))
  }

  def delete(id: Long) = inTransaction {
    AppDB.taskTable.deleteWhere(task =>
      task.id === id)
  }
}