package com.example.library.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class BookInformationRequest (
    @JsonProperty("id")
    var id: String,

    @JsonProperty("isbn_code")
    var isbnCode: String,

    @JsonProperty("write_by")
    var writeBy: String,

    @JsonProperty("name")
    var name: String,

    @JsonProperty("publisher")
    var publisher: String,

    @JsonProperty("category_id")
    var categoryId: String,

    @JsonProperty("amount")
    var amount: Int,

    @JsonProperty("price")
    var price: Int,

    @JsonProperty("created_by")
    var createdBy: String,

    @JsonProperty("created_datetime")
    var createdDateTime: LocalDateTime,

    @JsonProperty("updated_by")
    var updatedBy: String,

    @JsonProperty("updated_datetime")
    var updatedDateTime: LocalDateTime
)