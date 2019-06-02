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
            .foldLeft(Seq[LeaderboardRow]())((acc, row) => {
              val position: Int = if (acc.nonEmpty && row.score == acc.last.score) {
                acc.last.positionAsString.toInt
              } else {
                acc.length + 1
              }
              acc :+ LeaderboardRow(row.score, row.user, Some(position))
            })
        Leaderboard(leaderboard.code, positionedRows)
      }
      Ok(views.html.leaderboards(leaderboardsWithPositions))
    }
  }
}