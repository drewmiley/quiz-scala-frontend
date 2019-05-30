package controllers

import javax.inject.Inject
import play.api.mvc._
import play.api.libs.ws._

import scala.concurrent.{ExecutionContext, Future}

class Leaderboards @Inject()(ws: WSClient)(implicit ec: ExecutionContext) extends Controller {

  def get = Action {
    Ok(views.html.leaderboards("Leaderboards"))
  }

  def showSomeSiteContent = Action.async {
    ws.url("http://localhost:8080/api").get().map { response =>
      Ok(response.body)
    }
  }
}