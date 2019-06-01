package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class LeaderboardRow(score: Int = 0, user: String = "")

object LeaderboardRow {
  implicit val reads: Reads[LeaderboardRow] = (
    (__ \ "score").read[Int] and
      (__ \ "user").read[String]
    )(LeaderboardRow.apply _)
}
