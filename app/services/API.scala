package services

import javax.inject.Inject
import models.ValidQuizOptions
import play.api.Configuration
import play.api.cache.{SyncCacheApi, NamedCache}
import play.api.libs.json.Json
import play.api.libs.ws._

import scala.concurrent.{ExecutionContext, Future}

class API @Inject()(ws: WSClient, @NamedCache("session-cache") cache: SyncCacheApi, configuration: Configuration)(implicit ec: ExecutionContext) {
  private val endpoint = configuration.getString("api").getOrElse("http://localhost:8080/") + "api/"

  private val getValidQuizOptionsEndpoint = s"${endpoint}quizoptions"

  def getValidQuizOptions: Future[ValidQuizOptions] = {
    def getViaApi: Future[ValidQuizOptions] =
      ws.url(getValidQuizOptionsEndpoint).get().map { response =>
        val validQuizOptions = Json.parse(response.body).as[ValidQuizOptions]
        cache.set("validQuizOptions", validQuizOptions)
        validQuizOptions
      }
    cache.get[ValidQuizOptions]("validQuizOptions") map { Future.successful } getOrElse getViaApi
  }
}
