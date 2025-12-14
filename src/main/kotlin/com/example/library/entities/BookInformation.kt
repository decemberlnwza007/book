package com.example.library.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "book_information", schema = "book")
data class BookInformation (

    @Id
    @Column(name = "id")
    var bookId: UUID,

    @Column(name = "isbn_code")
    var isbnCode: String,

    @Column(name = "write_by")
    var writeBy: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "publisher")
    var publisher: String,

    @Column(name = "category_id")
    var categoryId: String,

    @Column(name = "amount")
    var amount: Int,

    @Column(name = "price")
    var price: Int,

    @Column(name = "created_by")
    var createdBy: String,

    @Column(name = "created_datetime")
    var createdDateTime: LocalDateTime,

    @Column(name = "updated_by")
    var updatedBy: String,

    @Column(name = "updated_datetime")
    var updatedDateTime: LocalDateTime
)