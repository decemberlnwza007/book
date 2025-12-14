package com.example.library.services.impl

import com.example.library.entities.DiscardBookTransactions
import com.example.library.exception.ConflictException
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.DiscardBookTransactionsRequest
import com.example.library.models.DiscardBookTransactionsResponse
import com.example.library.repositories.DiscardBookTransactionsRepository
import com.example.library.services.DiscardBookTransactionsService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DiscardBookTransactionsServiceImpl(
    private val discardBookRepository: DiscardBookTransactionsRepository,
) : DiscardBookTransactionsService {

    override fun getAllDiscardBooks(): List<DiscardBookTransactions> =
        discardBookRepository.findAll()

    override fun getDiscardBookById(id: UUID): DiscardBookTransactions {
        return discardBookRepository.findByDiscardBookId(id).orElseThrow {
            throw EntityNotFoundException("DiscardBook not found.")
        }
    }

    override fun postDiscardBook(discardBook: DiscardBookTransactionsRequest): DiscardBookTransactionsResponse {
        val existingDiscardBook = discardBookRepository.findByDiscardBookId(discardBook.id)
        if (existingDiscardBook.isPresent)  throw ConflictException("Already has this id")

        val addDiscardBook = DiscardBookTransactions(
            discardBookId = discardBook.id,
            bookId = discardBook.bookId,
            isbnCode = discardBook.isbnCode,
            name = discardBook.name,
            description = discardBook.description,
            userId = discardBook.userId,
            createdBy = discardBook.createdBy,
            createdDateTime = discardBook.createdDateTime,
            updatedBy = discardBook.updatedBy,
            updatedDateTime = discardBook.updatedDateTime
        )

        discardBookRepository.save(addDiscardBook)

        return DiscardBookTransactionsResponse(
            id = discardBook.id,
            bookId = discardBook.bookId,
            isbnCode = discardBook.isbnCode,
            name = discardBook.name,
            description = discardBook.description,
            userId = discardBook.userId,
            createdBy = discardBook.createdBy,
            createdDateTime = discardBook.createdDateTime,
            updatedBy = discardBook.updatedBy,
            updatedDateTime = discardBook.updatedDateTime
        )
    }

    override fun putDiscardBook(discardBook: DiscardBookTransactionsRequest): DiscardBookTransactionsResponse {

        val existingDiscardBook = discardBookRepository.findById(discardBook.id).orElseThrow {
            EntityNotFoundException("RepairBook not found.")
        }

        existingDiscardBook.apply {
            bookId = discardBook.bookId
            isbnCode = discardBook.isbnCode
            name = discardBook.name
            description = discardBook.description
            userId = discardBook.userId
            createdBy = discardBook.createdBy
            createdDateTime = discardBook.createdDateTime
            updatedBy = discardBook.updatedBy
            updatedDateTime = discardBook.updatedDateTime

            discardBookRepository.saveAndFlush(this)

            return DiscardBookTransactionsResponse(
                id = discardBook.id,
                bookId = discardBook.bookId,
                isbnCode = discardBook.isbnCode,
                name = discardBook.name,
                description = discardBook.description,
                userId = discardBook.userId,
                createdBy = discardBook.createdBy,
                createdDateTime = discardBook.createdDateTime,
                updatedBy = discardBook.updatedBy,
                updatedDateTime = discardBook.updatedDateTime
            )
        }
    }

    override fun deleteDiscardBook(id: UUID): ResponseEntity<*> {
        val existingDiscardBook = discardBookRepository.findByDiscardBookId(id).orElseThrow {
            throw EntityNotFoundException("Entity not found")
        }
        discardBookRepository.deleteById(existingDiscardBook.discardBookId)
        return ResponseEntity.accepted().body("")
    }
}
