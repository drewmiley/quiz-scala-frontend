package controllers

import javax.inject.Inject
import models.Leaderboard
import play.api.mvc._
import services.API

import scala.concurrent.ExecutionContext

class Quiz @Inject()(service: API)(implicit ec: ExecutionContext) extends Controller {

  def get = Action.async {
    for {
      questions <- service.getQuizByCode("040520197587")
      leaderboard <- service.getLeaderboardByCode("040520197587")
    } yield Ok(views.html.quiz("040520197587", questions, Leaderboard("040520197587", leaderboard)))
  }
}
