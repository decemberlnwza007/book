package com.example.library.services

import com.example.library.entities.RepairBookTransactions
import com.example.library.models.RepairBookTransactionsRequest
import com.example.library.models.RepairBookTransactionsResponse
import org.springframework.http.ResponseEntity
import java.util.UUID

interface RepairBookTransactionsService {

    fun getAllRepairBooks(): List<RepairBookTransactions>

    fun getRepairBookById(id: UUID): RepairBookTransactions

    fun postRepairBook(repairBook: RepairBookTransactionsRequest): RepairBookTransactionsResponse

    fun putRepairBook(repairBook: RepairBookTransactionsRequest): RepairBookTransactionsResponse

    fun deleteRepairBook(id: UUID): ResponseEntity<*>
}
