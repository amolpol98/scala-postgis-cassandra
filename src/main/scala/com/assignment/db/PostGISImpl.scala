package com.assignment.db

import com.assignment.dtos.http.request.UpdateToDoRequest
import com.assignment.dtos.model.datamodel.PostGISModel
import com.outworkers.phantom.Table
import com.outworkers.phantom.keys.PartitionKey
import com.outworkers.phantom.dsl._

import scala.concurrent.Future

abstract class PostGISImpl extends Table[PostGISImpl, PostGISModel]  {
  object id extends UUIDColumn with PartitionKey
  object imageRef extends StringColumn
  object pointType extends StringColumn
  override def tableName: String = "postgis"

  def insertPostGIS(postGISModel: PostGISModel): Future[PostGISModel] =
    insert
      .value(_.id, postGISModel.id)
      .value(_.imageRef, postGISModel.imageRef)
      .value(_.pointType, postGISModel.pointType)
      .future()
      .map(_ => postGISModel)

  def findPostGIS(id: UUID): Future[Option[PostGISModel]] =
    select.where(_.id eqs id).one()

  def updatePostGIS(postGISModel: PostGISModel): Future[PostGISModel] =
    update
      .where(_.id eqs postGISModel.id)
      .modify(_.imageRef setTo postGISModel.imageRef)
      .and(_.pointType setTo postGISModel.pointType)
      .ifExists
      .future()
      .map(rs => if (rs.wasApplied()) postGISModel else throw new Exception())
}