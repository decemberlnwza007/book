package com.example.library.controllers

import com.example.library.entities.RepairBookTransactions
import com.example.library.models.RepairBookTransactionsRequest
import com.example.library.models.RepairBookTransactionsResponse
import com.example.library.services.RepairBookTransactionsService
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
class RepairBookTransactionsController(
    private val repairBookService: RepairBookTransactionsService,
) {

    @GetMapping("/repair_books")
    fun getRepairBooks(): List<RepairBookTransactions> =
        repairBookService.getAllRepairBooks()

    @GetMapping("/repair_book")
    fun getRepairBook(
        @RequestParam("isbn_code") isbn: String
    ): RepairBookTransactions = repairBookService.getRepairBookByIsbnCode(isbn)

    @PostMapping("/repair_book")
    fun postRepairBook(
        @RequestBody repairBookRequest: RepairBookTransactionsRequest
    ): RepairBookTransactionsResponse = repairBookService.postRepairBook(repairBookRequest)

    @PutMapping("/repair_book")
    fun putRepairBook(
        @RequestBody repairBookRequest: RepairBookTransactionsRequest
    ): RepairBookTransactionsResponse = repairBookService.putRepairBook(repairBookRequest)




}
