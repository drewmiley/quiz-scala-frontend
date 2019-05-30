package controllers

import javax.inject.Inject
import models.ActionTypes
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext

class Init @Inject()(ws: WSClient)(implicit ec: ExecutionContext) extends Controller {

  def get = Action {
    Ok(views.html.init("Init"))
  }
}
