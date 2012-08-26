package controllers

import org.squeryl.PrimitiveTypeMode._

import com.codahale.jerkson.Json

import models.Task
import models.AppDB
import play.api._
import play.api.data._
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.data.Forms.optional
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def tasks = Action {
    Ok(views.html.index(Task.all, taskForm))
  }

  def apiTasks = Action {
    val json = Json.generate(Task.all)

    Ok(json).as(JSON)
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.value map { task =>
      inTransaction(AppDB.taskTable insert task)
      Redirect(routes.Application.tasks)
    } getOrElse BadRequest
  }

  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.tasks)
  }

  private val taskForm = Form(
    mapping(
      "label" -> nonEmptyText)(Task.apply)(Task.unapply))
}