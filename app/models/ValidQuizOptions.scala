package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class ValidQuizOptions(
                             category: Seq[String] = Seq(),
                             difficulty: Seq[String] = Seq(),
                             types: Seq[String] = Seq()
                           )

object ValidQuizOptions {
  implicit val reads: Reads[ValidQuizOptions] = (
    (__ \ "category").read[Seq[String]] and
      (__ \ "difficulty").read[Seq[String]] and
      (__ \ "type").read[Seq[String]]
    )(ValidQuizOptions.apply _)
}
