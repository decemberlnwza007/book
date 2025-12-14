package com.example.library.services

import com.example.library.entities.DiscardBookTransactions
import com.example.library.models.DiscardBookTransactionsRequest
import com.example.library.models.DiscardBookTransactionsResponse
import org.springframework.http.ResponseEntity
import java.util.UUID

interface DiscardBookTransactionsService {

    fun getAllDiscardBooks(): List<DiscardBookTransactions>

    fun getDiscardBookById(id: UUID): DiscardBookTransactions

    fun postDiscardBook(discardBook: DiscardBookTransactionsRequest): DiscardBookTransactionsResponse

    fun putDiscardBook(discardBook: DiscardBookTransactionsRequest): DiscardBookTransactionsResponse

    fun deleteDiscardBook(id: UUID): ResponseEntity<*>
}
