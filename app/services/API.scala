package services

import javax.inject.Inject
import models.ValidQuizOptions
import play.api.Configuration
import play.api.libs.json.Json
import play.api.libs.ws._

import scala.concurrent.{ExecutionContext, Future}

class API @Inject()(ws: WSClient, configuration: Configuration)(implicit ec: ExecutionContext) {
  private val endpoint = configuration.getString("api").getOrElse("http://localhost:8080/")

  def getValidQuizOptions: Future[ValidQuizOptions] = ws.url(s"${endpoint}api/quizoptions").get().map { response =>
    Json.parse(response.body).as[ValidQuizOptions]
  }
}
