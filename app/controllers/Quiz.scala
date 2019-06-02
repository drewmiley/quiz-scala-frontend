package controllers

import javax.inject.Inject
import play.api.mvc._
import services.API

import scala.concurrent.ExecutionContext

class Quiz @Inject()(service: API)(implicit ec: ExecutionContext) extends Controller {

  def get = Action.async {
    service.getQuizByCode("040520197587").map { questions =>
      Ok(views.html.quiz("040520197587", questions))
    }
  }
}
