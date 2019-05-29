package controllers

import play.api.mvc._

object Quiz extends Controller {

  def get = Action {
    Ok(views.html.quiz("Quiz"))
  }
}
