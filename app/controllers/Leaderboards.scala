package controllers

import javax.inject.Inject
import play.api.mvc._
import services.API
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}

import scala.concurrent.ExecutionContext

class Leaderboards @Inject()(service: API, cc: ControllerComponents, val messagesApi: MessagesApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def get = Action.async { implicit request =>
    val nameForm = Form("name" -> nonEmptyText)
    val name = nameForm.bindFromRequest().value.getOrElse("")
    service.getLeaderboardsByUser(name).map { leaderboards =>
      Ok(views.html.leaderboards(nameForm, leaderboards, routes.Leaderboards.get()))
    }
  }
}