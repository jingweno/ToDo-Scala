package models

import org.squeryl.Schema

object Database extends Schema {
  val taskTable = table[Task]("tasks")
}