package models

import play.api.libs.json._

case class SubmitAnswers(code: String, name: String, questionAnswers: Seq[(String, String)]) {
  val toRequestBody: JsObject = JsObject(Seq("answers" -> JsArray(
    questionAnswers map { qa => JsObject(
      Map(
        "question" -> JsString(qa._1),
        "answer" -> JsString(qa._2)
      )
    )}
  )))
}
