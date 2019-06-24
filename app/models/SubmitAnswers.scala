package models

import play.api.libs.json._

case class SubmitAnswers(code: String, name: String, questions: Seq[String], answers: Seq[String]) {
  // TODO: This should use QuestionAnswer model
  val toRequestBody: JsObject = JsObject(Seq("answers" -> JsArray(
    questions.zipWithIndex map { qI => JsObject(
      Map(
        "question" -> JsString(qI._1),
        "answer" -> JsString(answers(qI._2))
      )
    )}
  )))
}
