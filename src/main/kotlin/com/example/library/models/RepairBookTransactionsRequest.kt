package com.example.library.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.UUID

data class RepairBookTransactionsRequest(

    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("book_id")
    val bookId: String,

    @JsonProperty("isbn_code")
    val isbnCode: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("description")
    val description: String,

    @JsonProperty("user_id")
    val userId: String,

    @JsonProperty("created_by")
    val createdBy: String,

    @JsonProperty("created_datetime")
    val createdDateTime: LocalDateTime,

    @JsonProperty("updated_by")
    val updatedBy: String,

    @JsonProperty("updated_datetime")
    val updatedDateTime: LocalDateTime
)
