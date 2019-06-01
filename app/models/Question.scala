package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Question(
                     category: String = "",
                     questionType: String = "",
                     difficulty: String = "",
                     question: String = "",
                     answer: String = "",
                     incorrectAnswers: Seq[String] = Seq()
                   )

object Question {
  implicit val reads: Reads[Question] = (
    (__ \ "category").read[String] and
      (__ \ "questionType").read[String] and
      (__ \ "difficulty").read[String] and
      (__ \ "question").read[String] and
      (__ \ "answer").read[String] and
      (__ \ "incorrectAnswers").read[Seq[String]]
    )(Question.apply _)
}
