package models

import play.api.libs.json._

case class SubmitAnswers(code: String, name: String, questions: Seq[String], answers: Seq[String]) {
//  val toRequestBody: JsObject = ???
  val toRequestBody: JsObject = JsObject(Seq("answers" -> JsArray(
    answers map { d => JsString("sdfs") }
  )))
}
