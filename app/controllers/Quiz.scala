package controllers

import javax.inject.Inject
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext

class Quiz @Inject()(ws: WSClient)(implicit ec: ExecutionContext) extends Controller {

  def get = Action {
    Ok(views.html.quiz("Quiz"))
  }
}
