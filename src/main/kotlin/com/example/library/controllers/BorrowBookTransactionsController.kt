package com.example.library.controllers

import com.example.library.entities.BorrowBookTransactions
import com.example.library.models.BorrowBookTransactionsRequest
import com.example.library.models.BorrowBookTransactionsResponse
import com.example.library.services.BorrowBookTransactionsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class BorrowBookTransactionsController (
    private val borrowBookService: BorrowBookTransactionsService,
) {

    @GetMapping("/borrow_books")
    fun getBorrowBooks(): List<BorrowBookTransactions> = borrowBookService.getAllBorrowBooks()

    @GetMapping("/borrow_book")
    fun getBorrowBook(
        @RequestParam("id") id: UUID
    ) : BorrowBookTransactions = borrowBookService.getBorrowBook(id)

    @PostMapping("/borrow_book")
    fun postBorrowBook (
        @RequestBody borrowBookRequest: BorrowBookTransactionsRequest
    ): BorrowBookTransactionsResponse = borrowBookService.postBorrowBook(borrowBookRequest)

    @PutMapping("/borrow_book")
    fun putBorrowBook (
        @RequestBody borrowBookRequest: BorrowBookTransactionsRequest
    ): BorrowBookTransactionsResponse = borrowBookService.putBorrowBook(borrowBookRequest)

    @DeleteMapping("/borrow_book")
    fun deleteBorrowBook(
        @RequestParam id: UUID
    ): ResponseEntity<*> = borrowBookService.deleteBorrowBook(id)
}