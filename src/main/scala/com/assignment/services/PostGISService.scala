package com.assignment.services

import java.util.UUID

import com.assignment.dtos.http.request.{PostGISRequest, UpdateToDoRequest}
import com.assignment.dtos.model.datamodel.PostGISModel
import com.assignment.db.PostGISImpl
import com.datastax.driver.core.Session

import scala.concurrent.Future


class PostGISService(postGISImpl: PostGISImpl) {
  def create(createPostGIS: PostGISRequest): Future[PostGISModel] = postGISImpl
    .insertPostGIS(createPostGIS.toDataModel(generateItemId()))
  def retrieve(id: UUID): Future[Option[PostGISModel]] = postGISImpl.findPostGIS(id)
  def update(updatePostGIS: UpdateToDoRequest): Future[PostGISModel] = postGISImpl
    .updatePostGIS(updatePostGIS.toDataModel)

  private def generateItemId() = UUID.randomUUID()

}

object PostGISService {
  def apply(postGISImpl: PostGISImpl): PostGISService = new PostGISService(postGISImpl)
}
