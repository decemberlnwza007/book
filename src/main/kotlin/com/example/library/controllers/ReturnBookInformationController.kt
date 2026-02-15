package com.example.library.controllers

import com.example.library.entities.ReturnBookInformation
import com.example.library.models.ReturnBookInformationRequest
import com.example.library.models.ReturnBookInformationResponse
import com.example.library.services.ReturnBookInformationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ReturnBookInformationController(
    private val returnBookService: ReturnBookInformationService,
) {

    @GetMapping("/return_books")
    fun getReturnBooks(): List<ReturnBookInformation> = returnBookService.getAllReturnBooks()

    @GetMapping("/return_book")
    fun getReturnBook(
        @RequestParam("isbn_code") isbn: String
    ): ReturnBookInformation = returnBookService.getReturnBookByIsbnCode(isbn)

    @PostMapping("/return_book")
    fun postReturnBook(
        @RequestBody request: ReturnBookInformationRequest
    ): ReturnBookInformationResponse = returnBookService.postReturnBook(request)

    @PutMapping("/return_book")
    fun putReturnBook(
        @RequestBody request: ReturnBookInformationRequest
    ): ReturnBookInformationResponse = returnBookService.putReturnBook(request)

    @DeleteMapping("/return_book")
    fun deleteReturnBook(
        @RequestParam("id") id: String
    ): ResponseEntity<*> = returnBookService.deleteReturnBook(id)
}
