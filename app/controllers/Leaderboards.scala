package controllers

import javax.inject.Inject
import models.{Leaderboard, LeaderboardRow}
import play.api.mvc._
import services.API

import scala.concurrent.ExecutionContext

class Leaderboards @Inject()(service: API)(implicit ec: ExecutionContext) extends Controller {

  def get = Action.async {
    service.getLeaderboardsByUser().map { leaderboards =>
      val leaderboardsWithPositions = leaderboards.map { leaderboard =>
        val positionedRows = leaderboard.rows
            .sorted(Ordering.by[LeaderboardRow, Int](_.score).reverse)
            .zipWithIndex
            .foldLeft(Seq[(LeaderboardRow, Int)]())((acc, d) => {
              val row = d._1
              val position: Int = if (acc.nonEmpty && row.score == acc.last._1.score) {
                acc.last._1.position.get
              } else {
                acc.length + 1
              }
              acc :+ (LeaderboardRow(row.score, row.user, Some(position)), d._2)
            })
            .map(_._1)
        Leaderboard(leaderboard.code, positionedRows)
      }
      Ok(views.html.leaderboards(leaderboardsWithPositions))
    }
  }
}