package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Leaderboard(code: String = "", rows: Seq[LeaderboardRow] = Seq())

object Leaderboard {
  implicit val reads: Reads[Leaderboard] = (
    (__ \ "code").read[String] and
      (__ \ "results").read[Seq[LeaderboardRow]]
    )(Leaderboard.apply _)
}
