package com.assignment.dtos.http

import java.util.UUID
import com.assignment.dtos.model.datamodel.PostGISModel

object request {
  case class PostGISRequest(imageRef: String, pointType: String)
  object PostGISRequest {
    implicit class PostGISOps(postGISReq: PostGISRequest) {
      def toDataModel(id: UUID): PostGISModel =
        PostGISModel(id, postGISReq.imageRef, postGISReq.pointType)
    }
  }

  case class UpdateToDoRequest(id: UUID, imageRef: String, pointType: String)
  object UpdateToDoRequest {
    implicit class PostGISOps(updateToDo: UpdateToDoRequest) {
      def toDataModel: PostGISModel =
        PostGISModel(updateToDo.id, updateToDo.imageRef, updateToDo.pointType)
    }
  }
}
