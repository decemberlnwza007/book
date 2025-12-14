package com.example.library.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class UserInformationResponse (

    @JsonProperty("username")
    var username: String,

    @JsonProperty("password")
    var password: String,

    @JsonProperty("status")
    var status: String,

    @JsonProperty("permission")
    var permission: String,

    @JsonProperty("permission_level")
    var permission_level: String,

    @JsonProperty("created_by")
    var createdBy: String,

    @JsonProperty("created_datetime")
    var createdDateTime: LocalDateTime? = null,

    @JsonProperty("updated_by")
    var updatedBy: String,

    @JsonProperty("updated_datetime")
    var updatedDateTime: LocalDateTime? = null
)