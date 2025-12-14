package com.example.library.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class CategoryMasterResponse (
    @JsonProperty("id")
    var id: String,

    @JsonProperty("name")
    var name: String,

    @JsonProperty("created_by")
    var createdBy: String,

    @JsonProperty("created_datetime")
    var createdDateTime: LocalDateTime,

    @JsonProperty("updated_by")
    var updatedBy: String,

    @JsonProperty("updated_datetime")
    var updatedDateTime: LocalDateTime
)