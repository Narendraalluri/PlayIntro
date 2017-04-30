package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.modules.reactivemongo.json._
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import play.modules.reactivemongo.json.collection._

case class MongoObject(a: String)
object MongoObject {
  implicit val format = Json.format[MongoObject]
  implicit val writes = Json.writes[MongoObject]
}

class MongoTest @Inject() (val reactiveMongoApi: ReactiveMongoApi)
  extends Controller with MongoController with ReactiveMongoComponents {

  def index = Action.async {

    for {
      db <- database
      mongoObjectCollection <- db("test").find(Json.obj()).cursor[MongoObject]().collect[List]()
    } yield Ok(Json.toJson(mongoObjectCollection))

  }

}
