package controllers

import play.api._
import play.api.mvc._
import play.api.cache.Cache
import play.api.Play.current

object Init extends Controller {

  def get = Action {
    Ok(views.html.init("Init"))
  }
}
