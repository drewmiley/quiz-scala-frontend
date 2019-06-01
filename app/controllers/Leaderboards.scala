package controllers

import javax.inject.Inject
import play.api.mvc._
import services.API

import scala.concurrent.ExecutionContext

class Leaderboards @Inject()(service: API)(implicit ec: ExecutionContext) extends Controller {

  def get = Action.async {
    service.getLeaderboardsByUser().map { leaderboards =>
      val leaderboardsWithPositions = leaderboards
//      {props.leaderboard.sort((a, b) => b.score - a.score)
//        .reduce((acc, d) => {
//          const position = acc.length && d.score == acc[acc.length - 1].score ?
//            acc[acc.length - 1].position :
//            acc.length + 1;
//          return acc.concat([{ position, user: d.user, score: d.score }]);
//        }, [])
//        .map(d =>
//        <div key={d.user}>{d.position} - {d.user} - {d.score}</div>
//      )
//      }
//      <!--@{data.results.map(d =>-->
//        <!--<div key="@{d.user}">@{d.position} - @{d.user} - @{d.score}</div>-->
//        <!--)}
      Ok(views.html.leaderboards(leaderboardsWithPositions))
    }
  }
}