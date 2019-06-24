package controllers

import javax.inject.Inject
import models.{Leaderboard, SubmitAnswers}
import play.api.cache.{NamedCache, SyncCacheApi}
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.API

import scala.concurrent.{ExecutionContext, Future}

class Quiz @Inject()(service: API, @NamedCache("session-cache") cache: SyncCacheApi, val messagesApi: MessagesApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  val submitAnswersForm: Form[SubmitAnswers] = Form(
    mapping(
      "code" -> text(minLength = 1),
      "name" -> text(minLength = 1),
      "questions" -> seq(nonEmptyText),
      "answers" -> seq(nonEmptyText)
    ) { SubmitAnswers.apply } {
      submitAnswers => Some(submitAnswers.code, submitAnswers.name, submitAnswers.questions, submitAnswers.answers)
    }
  )

  def get(code: Option[String]) = Action.async { implicit request =>
    val quizCode = code map { Some(_) } getOrElse cache.get[String]("code")
    quizCode map { qCode =>
      for {
        questions <- service.getQuizByCode(qCode)
        leaderboard <- service.getLeaderboardByCode(qCode)
      } yield Ok(views.html.quiz(submitAnswersForm, qCode, questions, Leaderboard(qCode, leaderboard), routes.Quiz.submitAnswers))
    } getOrElse { Future.successful(InternalServerError("No quiz found")) }
  }

  def submitAnswers = Action.async { implicit request =>
    val submitAnswers = submitAnswersForm.bindFromRequest().value
    // TODO: Can explicit use of Some and None be removed
    submitAnswers map { sa =>
      for {
        code <- service.submitAnswers(sa)
      } yield Redirect(routes.Quiz.get(Some(code)))
    } getOrElse Future.successful(Redirect(routes.Quiz.get(None)))
  }
}
