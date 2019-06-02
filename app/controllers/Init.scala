package controllers

import javax.inject.Inject
import play.api.mvc._
import services.API

import scala.concurrent.ExecutionContext

class Init @Inject()(service: API)(implicit ec: ExecutionContext) extends Controller {

  def get = Action.async {
    for {
      validQuizCodes <- service.getValidQuizCodes()
      validQuizOptions <- service.getValidQuizOptions
    } yield Ok(views.html.init("", "", validQuizCodes, validQuizOptions))
  }
}
