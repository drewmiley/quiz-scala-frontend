package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class LeaderboardRow(score: Int = 0, user: String = "", position: Option[Int] = None) {
  val positionAsString: String = position map { _.toString } getOrElse "NotFound"
}

object LeaderboardRow {
  implicit val reads: Reads[LeaderboardRow] = (
    (__ \ "score").read[Int] and
      (__ \ "user").read[String]
    )((score, user) => LeaderboardRow.apply(score, user))
}
