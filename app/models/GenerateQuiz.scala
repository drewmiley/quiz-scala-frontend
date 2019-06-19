package models

import play.api.libs.json.{JsObject, JsString}

case class GenerateQuiz(amount: String, category: String, difficulty: String, types: String) {
  val toRequestBody: JsObject = {
    JsObject(Seq("options" -> JsObject(
      Map(
        "amount" -> JsString(amount),
        "difficulty" -> JsString(difficulty),
        "category" -> JsString(category),
        "type" -> JsString(types)
      )
    )))
  }
}