package controllers

import javax.inject.Inject
import models.Leaderboard
import play.api.cache.{NamedCache, SyncCacheApi}
import play.api.mvc._
import services.API

import scala.concurrent.{ExecutionContext, Future}

class Quiz @Inject()(service: API, @NamedCache("session-cache") cache: SyncCacheApi)(implicit ec: ExecutionContext) extends Controller {

  def get(code: Option[String]) = Action.async { implicit request =>
    cache.get[String]("code") map { code =>
      for {
        questions <- service.getQuizByCode(code)
        leaderboard <- service.getLeaderboardByCode(code)
      } yield Ok(views.html.quiz(code, questions, Leaderboard(code, leaderboard)))
    } getOrElse { Future.successful(Ok(views.html.quiz())) }
  }
}
