package controllers

import play.api.libs.ws
import play.api.mvc._
import play.api.libs.ws._

import scala.concurrent.Future

object Leaderboards extends Controller {

  def get = Action {
    Ok(views.html.leaderboards("Leaderboards"))
  }
}