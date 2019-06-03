package controllers

import javax.inject.Inject
import play.api.mvc._
import services.API
  import play.api.data._
  import play.api.data.Forms._

import scala.concurrent.ExecutionContext

class Leaderboards @Inject()(service: API)(implicit ec: ExecutionContext) extends Controller {

  def get = Action.async {
    service.getLeaderboardsByUser().map { leaderboards =>
      val nameForm = Form("name" -> text())
      Ok(views.html.leaderboards(nameForm, leaderboards))
    }
  }
}