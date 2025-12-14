package com.example.library.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDateTime

data class ReturnBookInformationResponse(

    @JsonProperty("id")
    val id: String,

    @JsonProperty("isbn_code")
    val isbnCode: String,

    @JsonProperty("price")
    val price: BigDecimal,

    @JsonProperty("book_id")
    val bookId: String,

    @JsonProperty("publisher")
    val publisher: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("status")
    val status: String,

    @JsonProperty("amount")
    val amount: Int,

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
