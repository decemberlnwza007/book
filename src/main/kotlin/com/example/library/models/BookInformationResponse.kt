package com.example.library.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class BookInformationResponse (
//    @JsonProperty("id")
//    var bookId: String,

    @JsonProperty("isbn_code")
    var isbnCode: String? = null,

    @JsonProperty("write_by")
    var writeBy: String? = null,

    @JsonProperty("name")
    var name: String? = null,

    @JsonProperty("publisher")
    var publisher: String? = null,

    @JsonProperty("category_id")
    var categoryId: String? = null,

    @JsonProperty("amount")
    var amount: Int? = null,

    @JsonProperty("price")
    var price: Int? = null
)