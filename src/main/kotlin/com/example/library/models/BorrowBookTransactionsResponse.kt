package com.example.library.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.cglib.core.Local
import java.time.LocalDateTime
import java.util.UUID

data class BorrowBookTransactionsResponse (

    @JsonProperty("isbn_code")
    val isbnCode: String? = null,

    @JsonProperty("student_id")
    val studentId: String? = null,

    @JsonProperty("book_id")
    val bookId: String? = null,

    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("status")
    val status: String? = null,

    @JsonProperty("price")
    val price: Int? = null,

    @JsonProperty("borrow_date")
    val borrowDate: String? = null,

    @JsonProperty("return_date")
    val returnDate: String? = null,

    @JsonProperty("user_id")
    val userId: String? = null,

    @JsonProperty("created_by")
    var createdBy: String? = null,

    @JsonProperty("created_datetime")
    var createdDateTime: LocalDateTime? = null,

    @JsonProperty("updated_by")
    var updatedBy: String? = null,

    @JsonProperty("updated_datetime")
    var updatedDateTime: LocalDateTime? = null
)