package controllers

import javax.inject.Inject
import play.api.Configuration
import play.api.mvc._
import play.api.libs.ws._

import scala.concurrent.{ExecutionContext, Future}

class Leaderboards @Inject()(ws: WSClient, configuration: Configuration)(implicit ec: ExecutionContext) extends Controller {

  def get = Action {
    Ok(views.html.leaderboards("Leaderboards"))
  }

  def showSomeSiteContent = Action.async {
    val api = configuration.getString("api").getOrElse("http://localhost:8080/")
    ws.url(s"${api}api").get().map { response =>
      Ok(response.body)
    }
  }
}