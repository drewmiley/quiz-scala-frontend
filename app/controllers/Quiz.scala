package controllers

import javax.inject.Inject
import models.Leaderboard
import play.api.cache.{NamedCache, SyncCacheApi}
import play.api.mvc._
import services.API

import scala.concurrent.ExecutionContext

class Quiz @Inject()(service: API, @NamedCache("session-cache") cache: SyncCacheApi)(implicit ec: ExecutionContext) extends Controller {

  def get = Action.async {
    val code = cache.get[String]("code").get
    for {
      questions <- service.getQuizByCode(code)
      leaderboard <- service.getLeaderboardByCode(code)
    } yield Ok(views.html.quiz(code, questions, Leaderboard(code, leaderboard)))
  }
}
