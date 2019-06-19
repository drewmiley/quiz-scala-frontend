package controllers

import javax.inject.Inject
import models.Leaderboard
import play.api.cache.{NamedCache, SyncCacheApi}
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.API

import scala.concurrent.{ExecutionContext, Future}

class Quiz @Inject()(service: API, @NamedCache("session-cache") cache: SyncCacheApi, val messagesApi: MessagesApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def get(code: Option[String]) = Action.async { implicit request =>
    val nameForm = Form("name" -> nonEmptyText)
    val name = nameForm.bindFromRequest().value.getOrElse("")
    val quizCode = code map { Some(_) } getOrElse cache.get[String]("code")
    quizCode map { qCode =>
      for {
        questions <- service.getQuizByCode(qCode)
        leaderboard <- service.getLeaderboardByCode(qCode)
      } yield Ok(views.html.quiz(nameForm, qCode, questions, Leaderboard(qCode, leaderboard), routes.Quiz.get(code)))
    } getOrElse { Future.successful(Ok(views.html.quiz(nameForm, getQuizUrl = routes.Quiz.get(code)))) }
  }
}
