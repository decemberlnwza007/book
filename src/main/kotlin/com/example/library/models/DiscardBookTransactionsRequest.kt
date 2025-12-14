package com.example.library.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.UUID

data class DiscardBookTransactionsRequest(

    @JsonProperty("id")
    var id: UUID,

    @JsonProperty("book_id")
    var bookId: String,

    @JsonProperty("isbn_code")
    var isbnCode: String,

    @JsonProperty("name")
    var name: String,

    @JsonProperty("description")
    var description: String,

    @JsonProperty("user_id")
    var userId: String,

    @JsonProperty("created_by")
    var createdBy: String,

    @JsonProperty("created_datetime")
    var createdDateTime: LocalDateTime,

    @JsonProperty("updated_by")
    var updatedBy: String,

    @JsonProperty("updated_datetime")
    var updatedDateTime: LocalDateTime
)
