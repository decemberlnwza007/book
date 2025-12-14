package com.example.library.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.cglib.core.Local
import java.time.LocalDateTime
import java.util.UUID

data class BorrowBookTransactionsResponse (
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("isbn_code")
    val isbnCode: String,

    @JsonProperty("student_id")
    val studentId: String,

    @JsonProperty("book_id")
    val bookId: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("status")
    val status: String,

    @JsonProperty("price")
    val price: String,

    @JsonProperty("borrow_date")
    val borrowDate: String,

    @JsonProperty("return_date")
    val returnDate: String,

    @JsonProperty("user_id")
    val userId: String,

    @JsonProperty("created_by")
    var createdBy: String,

    @JsonProperty("created_datetime")
    var createdDateTime: LocalDateTime,

    @JsonProperty("updated_by")
    var updatedBy: String,

    @JsonProperty("updated_datetime")
    var updatedDateTime: LocalDateTime
)