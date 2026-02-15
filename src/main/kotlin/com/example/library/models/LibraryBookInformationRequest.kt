package com.example.library.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.UUID

data class LibraryBookInformationRequest(
    @JsonProperty("id")
    var id: UUID? = null,

    @JsonProperty("book_id")
    var bookId: String? = null,

    @JsonProperty("isbn_code")
    var isbnCode: String? = null,

    @JsonProperty("book_name")
    var bookName: String? = null,

    @JsonProperty("status")
    var status: String? = null,

    @JsonProperty("created_by")
    var createdBy: String? = null,

    @JsonProperty("created_datetime")
    var createdDatetime: LocalDateTime,

    @JsonProperty("updated_by")
    var updatedBy: String? = null,

    @JsonProperty("updated_datetime")
    var updatedDatetime: String? = null
)
