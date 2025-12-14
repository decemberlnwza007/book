package com.example.library.controllers

import com.example.library.entities.DiscardBookTransactions
import com.example.library.models.DiscardBookTransactionsRequest
import com.example.library.models.DiscardBookTransactionsResponse
import com.example.library.services.DiscardBookTransactionsService
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
class DiscardBookTransactionsController(
    private val discardBookService: DiscardBookTransactionsService,
) {

    @GetMapping("/discard_books")
    fun getDiscardBooks(): List<DiscardBookTransactions> = discardBookService.getAllDiscardBooks()

    @GetMapping("/discard_book")
    fun getDiscardBook(
        @RequestParam("id") id: UUID
    ): DiscardBookTransactions = discardBookService.getDiscardBookById(id)

    @PostMapping("/discard_book")
    fun postDiscardBook(
        @RequestBody discardBookRequest: DiscardBookTransactionsRequest
    ): DiscardBookTransactionsResponse = discardBookService.postDiscardBook(discardBookRequest)

    @PutMapping("/discard_book")
    fun putDiscardBook(
        @RequestBody discardBookRequest: DiscardBookTransactionsRequest
    ): DiscardBookTransactionsResponse = discardBookService.putDiscardBook(discardBookRequest)

    @DeleteMapping("/discard_book")
    fun deleteDiscardBook(
        @RequestParam("id") id: UUID
    ): ResponseEntity<*> = discardBookService.deleteDiscardBook(id)
}
