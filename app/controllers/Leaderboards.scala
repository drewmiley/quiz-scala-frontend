package controllers

import javax.inject.Inject
import play.api.mvc._
import services.API

import scala.concurrent.ExecutionContext

class Leaderboards @Inject()(service: API)(implicit ec: ExecutionContext) extends Controller {

  def get = Action {
    Ok(views.html.leaderboards("Leaderboards"))
  }

  def showSomeSiteContent = Action.async {
    service.getValidQuizCodes().map { validQuizCodes =>
      Ok(validQuizCodes.toString)
    }
  }
}