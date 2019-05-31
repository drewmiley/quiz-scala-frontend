package services

import javax.inject.Inject
import play.api.Configuration
import play.api.libs.ws._

import scala.concurrent.{ExecutionContext, Future}

class API @Inject()(ws: WSClient, configuration: Configuration)(implicit ec: ExecutionContext) {
  private val endpoint = configuration.getString("api").getOrElse("http://localhost:8080/")

  def getValidQuizOptions: Future[WSResponse] = ws.url(s"${endpoint}api").get()
}
