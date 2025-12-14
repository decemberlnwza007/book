package com.example.library.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "borrow_book_transactions", schema = "book")
data class BorrowBookTransactions (

    @Id
    @Column(name = "id")
    var borrowBookId: UUID,

    @Column(name = "isbn_code")
    var isbnCode: String,

    @Column(name = "student_id")
    var studentId: String,

    @Column(name = "book_id")
    var bookId: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "status")
    var status: String,

    @Column(name = "price")
    var price: String,

    @Column(name = "borrow_date")
    var borrowDate: String,

    @Column(name = "return_date")
    var returnDate: String,

    @Column(name = "created_by")
    var createdBy: String,

    @Column(name = "created_datetime")
    var createdDateTime: LocalDateTime,

    @Column(name = "updated_by")
    var updatedBy: String,

    @Column(name = "updated_datetime")
    var updatedDateTime: LocalDateTime,

    @Column(name = "user_id")
    var userId: String

    )