package models

case class Store(
                  code: String = "",
                  quiz: Seq[Question] = Seq(),
                  leaderboard: Seq[LeaderboardRow] = Seq(),
                  leaderboards: Seq[Leaderboard] = Seq(),
                  answers: Seq[QuestionAnswer] = Seq(),
                  validQuizCodes: Seq[String] = Seq(),
                  validQuizOptions: ValidQuizOptions = ValidQuizOptions()
                )
