package models

import play.api.libs.json.JsObject

case class SubmitAnswers(code: String, name: String, answers: Seq[String]) {
//  val toRequestBody: JsObject = ???
  val toRequestBody: String = code
}
