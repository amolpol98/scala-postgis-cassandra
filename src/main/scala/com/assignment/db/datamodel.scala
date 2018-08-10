package com.assignment.db

import java.util.UUID

object datamodel {
  case class PostGISModel(id: UUID,
                          imageRef: String,
                          pointType: String)
}