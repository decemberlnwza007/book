package com.example.library.controllers

import com.example.library.entities.WithdrawBookInformation
import com.example.library.models.WithdrawBookInformationRequest
import com.example.library.models.WithdrawBookInformationResponse
import com.example.library.services.WithdrawBookInformationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class WithdrawBookInformationController(
    private val withdrawBookService: WithdrawBookInformationService,
) {

    @GetMapping("/withdraw_books")
    fun getWithdrawBooks(): List<WithdrawBookInformation> = withdrawBookService.getAllWithdrawBooks()

    @GetMapping("/withdraw_book")
    fun getWithdrawBook(
        @RequestParam("isbn_code") isbn: String
    ): WithdrawBookInformation = withdrawBookService.getWithdrawBookByIsbnCode(isbn)

    @PostMapping("/withdraw_book")
    fun postWithdrawBook(
        @RequestBody request: WithdrawBookInformationRequest
    ): WithdrawBookInformationResponse = withdrawBookService.postWithdrawBook(request)

    @PutMapping("/withdraw_book")
    fun putWithdrawBook(
        @RequestBody request: WithdrawBookInformationRequest
    ): WithdrawBookInformationResponse = withdrawBookService.putWithdrawBook(request)

}
