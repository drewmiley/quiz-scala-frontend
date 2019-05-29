package controllers

import play.api.mvc._

object Leaderboards extends Controller {

  def get = Action {
    Ok(views.html.leaderboards("Leaderboards"))
  }
}
