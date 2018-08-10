package com.assignment.dtos.model

import java.util.UUID
import com.assignment.dtos.http.response.PostGISResponse

object datamodel {
  case class PostGISModel(id: UUID,
                          imageRef: String,
                          pointType: String)
  object PostGISModel {
    implicit class PostGISModelOps(postGISModel: PostGISModel) {
      def toDomainModel: PostGISResponse =
        PostGISResponse(postGISModel.id, postGISModel.imageRef, postGISModel.pointType)
    }
  }
}
