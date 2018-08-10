package com.assignment.dtos.http

import java.util.UUID

object response {
  case class PostGISResponse(id: UUID, imageRef: String, pointType: String)
}
