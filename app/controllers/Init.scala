package controllers

import javax.inject.Inject
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.API

import scala.concurrent.{ExecutionContext, Future}

class Init @Inject()(service: API, val messagesApi: MessagesApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def get = Action.async { implicit request =>
    val loadQuizForm: Form[String] = Form("code" -> nonEmptyText)
    val generateQuizForm: Form[String] = Form("code" -> nonEmptyText)
    for {
      validQuizCodes <- service.getValidQuizCodes()
      validQuizOptions <- service.getValidQuizOptions
    } yield Ok(views.html.init(
      loadQuizForm,
      routes.Init.loadQuiz(),
      generateQuizForm,
      routes.Init.generateQuiz(),
      validQuizCodes,
      validQuizOptions
    ))
  }

  def generateQuiz = Action.async { implicit request =>
    Future.successful(Redirect(routes.Quiz.get(Form("code" -> nonEmptyText).bindFromRequest().value)))
  }

  def loadQuiz = Action.async { implicit request =>
    Future.successful(Redirect(routes.Quiz.get(Form("code" -> nonEmptyText).bindFromRequest().value)))
  }
}
