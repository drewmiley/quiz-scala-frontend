package services

import javax.inject.Inject
import models._
import play.api.Configuration
import play.api.cache.{NamedCache, SyncCacheApi}
import play.api.libs.json.Json
import play.api.libs.ws._

import scala.concurrent.{ExecutionContext, Future}

class API @Inject()(ws: WSClient, @NamedCache("session-cache") cache: SyncCacheApi, configuration: Configuration)(implicit ec: ExecutionContext) {
  private val endpoint = configuration.getString("api").getOrElse("http://localhost:8080/") + "api/"

  private val getValidQuizOptionsEndpoint = s"${endpoint}quizoptions"
  private val getValidQuizCodesEndpoint = s"${endpoint}quizcodes"
  private def getQuizByCodeEndpoint(code: String) = s"${endpoint}quiz/$code"
  private def getLeaderboardByCodeEndpoint(code: String) = s"${endpoint}leaderboard/$code"
  private def getLeaderboardsByUserEndpoint(user: String = "") =
    s"${endpoint}leaderboards${if(user.nonEmpty) s"?user=$user" else ""}"

  def getValidQuizOptions: Future[ValidQuizOptions] = {
    def getViaApi: Future[ValidQuizOptions] =
      ws.url(getValidQuizOptionsEndpoint).get().map { response =>
        val validQuizOptions = Json.parse(response.body).as[ValidQuizOptions]
        cache.set("validQuizOptions", validQuizOptions)
        validQuizOptions
      }
    cache.get[ValidQuizOptions]("validQuizOptions") map { Future.successful } getOrElse getViaApi
  }

  def getValidQuizCodes(forceViaApi: Boolean = false): Future[Seq[String]] = {
    def getViaApi: Future[Seq[String]] =
      ws.url(getValidQuizCodesEndpoint).get().map { response =>
        val validQuizCodes = Json.parse(response.body).as[Seq[String]]
        cache.set("validQuizCodes", validQuizCodes)
        validQuizCodes
      }
    val cacheValue: Option[Seq[String]] = cache.get[Seq[String]]("validQuizCodes")
    if (forceViaApi) {
      getViaApi
    } else {
      cacheValue map { Future.successful } getOrElse getViaApi
    }
  }

  def getQuizByCode(code: String): Future[Seq[Question]] =
    ws.url(getQuizByCodeEndpoint(code)).get().map { response =>
      (Json.parse(response.body) \ "quiz").as[Seq[Question]]
    }

  def getLeaderboardByCode(code: String): Future[Seq[LeaderboardRow]] =
    ws.url(getLeaderboardByCodeEndpoint(code)).get().map { response =>
      (Json.parse(response.body) \ "results").as[Seq[LeaderboardRow]]
    }

  def getLeaderboardsByUser(user: String = ""): Future[Seq[Leaderboard]] =
    ws.url(getLeaderboardsByUserEndpoint(user)).get().map { response =>
      Json.parse(response.body).as[Seq[Leaderboard]]
    }
}
