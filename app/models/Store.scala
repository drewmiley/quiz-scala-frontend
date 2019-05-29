package models

case class Store(
                  code: String = "",
                  quiz: Seq[String] = Seq(),
                  leaderboard: Seq[String] = Seq(),
                  leaderboards: Seq[String] = Seq(),
                  answers: Seq[String] = Seq(),
                  validQuizCodes: Seq[String] = Seq(),
                  validQuizOptions: ValidQuizOptions = ValidQuizOptions()
                )
