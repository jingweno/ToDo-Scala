package models

import scala.collection.immutable.List

import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode.__thisDsl
import org.squeryl.PrimitiveTypeMode.from
import org.squeryl.PrimitiveTypeMode.inTransaction
import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.PrimitiveTypeMode.select

case class Task(label: String) extends KeyedEntity[Long] {
  val id: Long = 0
}

object Task {
  def all(): List[Task] = inTransaction {
    from(Database.taskTable)(taskTable =>
      select(taskTable)).toList
  }

  def create(task: Task) = inTransaction {
    Database.taskTable.insert(task)
  }

  def delete(id: Long) = inTransaction {
    Database.taskTable.deleteWhere(task =>
      task.id === id)
  }
}