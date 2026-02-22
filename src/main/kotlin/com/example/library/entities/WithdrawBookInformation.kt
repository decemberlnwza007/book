package com.example.library.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "withdraw_book_information", schema = "book")
data class WithdrawBookInformation (

    @Id
    @Column(name = "id")
    var withdrawId: String,

    @Column(name = "isbn_code")
    var isbnCode: String,

    @Column(name = "book_id")
    var bookId: String,

    @Column(name = "publisher")
    var publisher: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "price")
    var price: BigDecimal,

    @Column(name = "status")
    var status: String,

    @Column(name = "user_id")
    var userId: String,

    @Column(name = "amount")
    var amount: Int,

    @Column(name = "created_by")
    var createdBy: String,

    @Column(name = "created_datetime")
    var createdDateTime: LocalDateTime?,

    @Column(name = "updated_by")
    var updatedBy: String,

    @Column(name = "updated_datetime")
    var updatedDateTime: LocalDateTime?,
)