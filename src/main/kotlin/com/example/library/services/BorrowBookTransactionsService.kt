package com.example.library.services

import com.example.library.entities.BorrowBookTransactions
import com.example.library.models.BorrowBookTransactionsRequest
import com.example.library.models.BorrowBookTransactionsResponse
import org.springframework.http.ResponseEntity
import java.util.UUID

interface BorrowBookTransactionsService {
    fun getAllBorrowBooks(): List<BorrowBookTransactions>

    fun getBorrowBook(id: UUID) : BorrowBookTransactions

    fun postBorrowBook(borrow: BorrowBookTransactionsRequest): BorrowBookTransactionsResponse

    fun putBorrowBook(borrow: BorrowBookTransactionsRequest): BorrowBookTransactionsResponse

    fun deleteBorrowBook(id: UUID): ResponseEntity<*>
}