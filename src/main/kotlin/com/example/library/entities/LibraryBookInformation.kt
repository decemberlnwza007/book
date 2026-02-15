package com.example.library.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "library_book_information", schema = "book")
class LibraryBookInformation (

    @Id
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "book_id")
    var bookId: String?,

    @Column(name = "isbn_code")
    var isbnCode: String?,

    @Column(name = "status")
    var status: String?,

    @Column(name = "created_by")
    var createdBy: String?,

    @Column(name = "updated_by")
    var updatedBy: String?,

    @Column(name = "updated_datetime")
    var updatedDatetime: String?,

    @Column(name = "created_datetime")
    var createdDatetime: LocalDateTime
)