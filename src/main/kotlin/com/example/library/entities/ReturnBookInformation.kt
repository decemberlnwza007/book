package com.example.library.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "return_book_information", schema = "book")
data class ReturnBookInformation (

    @Id
    @Column(name = "id")
    var returnBookId: String,

    @Column(name = "isbn_code")
    var isbnCode: String,

    @Column(name = "price")
    var price: BigDecimal,

    @Column(name = "book_id")
    var bookId: String,

    @Column(name = "publisher")
    var publisher: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "status")
    var status: String,

    @Column(name = "created_by")
    var createdBy: String,

    @Column(name = "created_datetime")
    var createdDateTime: LocalDateTime?,

    @Column(name = "updated_by")
    var updatedBy: String,

    @Column(name = "updated_datetime")
    var updatedDateTime: LocalDateTime?,

    @Column(name = "amount")
    var amount: Int,

    @Column(name = "user_id")
    var userId: String,
)