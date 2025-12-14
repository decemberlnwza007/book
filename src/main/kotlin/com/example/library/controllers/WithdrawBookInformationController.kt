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
        @RequestParam("id") id: String
    ): WithdrawBookInformation = withdrawBookService.getWithdrawBookById(id)

    @PostMapping("/withdraw_book")
    fun postWithdrawBook(
        @RequestBody request: WithdrawBookInformationRequest
    ): WithdrawBookInformationResponse = withdrawBookService.postWithdrawBook(request)

    @PutMapping("/withdraw_book")
    fun putWithdrawBook(
        @RequestBody request: WithdrawBookInformationRequest
    ): WithdrawBookInformationResponse = withdrawBookService.putWithdrawBook(request)

    @DeleteMapping("/withdraw_book")
    fun deleteWithdrawBook(
        @RequestParam("id") id: String
    ): ResponseEntity<*> = withdrawBookService.deleteWithdrawBook(id)
}
