package controllers

import com.codahale.jerkson.Json

import models.Task
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.Play.current
import play.api.libs.concurrent.Akka

object Application extends Controller {
  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def tasks = Action {
    val promiseOfTasks = Akka.future {
      Task.all
    }

    Async {
      promiseOfTasks.map(tasks => Ok(views.html.index(tasks, taskForm)))
    }
  }

  def apiTasks = Action {
    val promiseOfJson = Akka.future {
      Json.generate(Task.all)
    }

    Async {
      promiseOfJson.map(json => Ok(json).as(JSON))
    }
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.value map { task =>
      val promiseOfTask = Akka.future {
        Task.create(task)
      }

      Async {
        promiseOfTask.map(task => Redirect(routes.Application.tasks))
      }

    } getOrElse BadRequest
  }

  def deleteTask(id: Long) = Action {
    val promiseOfDeleteTask = Akka.future {
      Task.delete(id)
    }

    Async {
      promiseOfDeleteTask.map(taskId => Redirect(routes.Application.tasks))
    }
  }

  private val taskForm = Form(
    mapping(
      "label" -> nonEmptyText)(Task.apply)(Task.unapply))
}