package models

case class Question(
                     category: String = "",
                     questionType: String = "",
                     difficulty: String = "",
                     question: String = "",
                     answer: String = "",
                     incorrectAnswers: Seq[String] = Seq()
                   )
