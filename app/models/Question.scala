package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Question(
                     question: String = "",
                     answer: String = "",
                     incorrectAnswers: Seq[String] = Seq()
                   ) {
  val answers: Seq[String] = (incorrectAnswers :+ answer)
    .sortWith((_, _) => math.random < 0.5)
}

object Question {
  implicit val reads: Reads[Question] = (
    (__ \ "question").read[String] and
      (__ \ "answer").read[String] and
      (__ \ "incorrectAnswers").read[Seq[String]]
    )(Question.apply _)
}
