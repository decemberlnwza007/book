package com.example.library.controllers

import com.example.library.entities.BookInformation
import com.example.library.models.BookInformationRequest
import com.example.library.models.BookInformationResponse
import com.example.library.services.BookInformationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BookInformationController (
    private val bookInformationService: BookInformationService,
) {

    @GetMapping("/books")
    fun getBooks() : List<BookInformationResponse> {
        return bookInformationService.getAllBooks()
    }

    @GetMapping("/book")
    fun getBook(@RequestParam("isbn_code") isbn: String) : BookInformationResponse = bookInformationService.getBookByIsbnCode(isbn)

    @PostMapping("/book")
    fun postBook(
        @RequestBody bookInformationRequest: BookInformationRequest
    ) : BookInformationResponse = bookInformationService.postBook(bookInformationRequest)

    @PutMapping("/book")
    fun putBook (
        @RequestBody bookInformationRequest: BookInformationRequest
    ) : BookInformationResponse = bookInformationService.putBook(bookInformationRequest)

    @DeleteMapping("/book")
    fun deleteBook(@RequestParam("id") id: String) : ResponseEntity<*> = bookInformationService.deleteBook(id)
}