package controllers

import javax.inject.Inject
import play.api.mvc._
import services.API
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}

import scala.concurrent.ExecutionContext

class Leaderboards @Inject()(service: API, cc: ControllerComponents, val messagesApi: MessagesApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {
  val nameForm = Form("name" -> text())

  def get = Action.async { implicit request =>
    service.getLeaderboardsByUser().map { leaderboards =>
      Ok(views.html.leaderboards(nameForm, leaderboards))
    }
  }

  def getByUser() = Action { implicit request =>
    val formData = nameForm.bindFromRequest.get // Careful: BasicForm.form.bindFromRequest returns an Option
    Ok(formData.toString) // just returning the data because it's an example :)
  }
}