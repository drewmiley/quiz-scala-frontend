package controllers

import javax.inject.Inject
import models.GenerateQuiz
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.API

import scala.concurrent.{ExecutionContext, Future}

class Init @Inject()(service: API, val messagesApi: MessagesApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  val loadQuizForm: Form[String] = Form("code" -> nonEmptyText)
  val generateQuizForm: Form[GenerateQuiz] = Form(
    mapping(
      "amount" -> nonEmptyText,
    "category" -> nonEmptyText,
  "difficulty" -> nonEmptyText,
  "types" -> nonEmptyText
    ) { GenerateQuiz.apply } {
      gQuiz => Some((gQuiz.amount, gQuiz.category, gQuiz.difficulty, gQuiz.types))
    }
  )

  def get = Action.async { implicit request =>
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
    val generateQuiz = generateQuizForm.bindFromRequest().value
    service.generateQuiz(generateQuiz) map { code =>  Redirect(routes.Quiz.get(code)) }
  }

  def loadQuiz = Action.async { implicit request =>
    Future.successful(Redirect(routes.Quiz.get(loadQuizForm.bindFromRequest().value)))
  }
}
