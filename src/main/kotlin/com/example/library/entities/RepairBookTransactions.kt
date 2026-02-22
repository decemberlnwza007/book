package com.example.library.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "repair_book_transactions", schema = "book")
data class RepairBookTransactions (

    @Id
    @Column(name = "id")
    var repairBookId: UUID,

    @Column(name = "book_id")
    var bookId: String,

    @Column(name = "isbn_code")
    var isbnCode: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "user_id")
    var userId: String,

    @Column(name = "created_by")
    var createdBy: String,

    @Column(name = "created_datetime")
    var createdDateTime: LocalDateTime?,

    @Column(name = "updated_by")
    var updatedBy: String,

    @Column(name = "updated_datetime")
    var updatedDateTime: LocalDateTime?,
)