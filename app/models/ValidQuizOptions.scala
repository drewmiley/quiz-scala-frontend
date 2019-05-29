package models

case class ValidQuizOptions(
                             category: Seq[String] = Seq(),
                             difficulty: Seq[String] = Seq(),
                             types: Seq[String] = Seq()
                           )
