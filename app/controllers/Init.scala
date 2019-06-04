package controllers

import javax.inject.Inject
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.API

import scala.concurrent.ExecutionContext

class Init @Inject()(service: API, val messagesApi: MessagesApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def get = Action.async { implicit request =>
    for {
      validQuizCodes <- service.getValidQuizCodes()
      validQuizOptions <- service.getValidQuizOptions
    } yield Ok(views.html.init(Form("code" -> nonEmptyText), routes.Quiz.get(), "", validQuizCodes, validQuizOptions))
  }
}
