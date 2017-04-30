package controllers

import play.api.libs.json.Json
import play.api.mvc._

case class MyResult(resultCode: Int, resultTextMessage: String)


class Application extends Controller {

  implicit val resultWrites = Json.writes[MyResult]

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def jsonTest = Action {
    Ok(Json.toJson(MyResult(1, "sdf")))
  }

}
