package com.example.library.services.impl

import com.example.library.entities.DiscardBookTransactions
import com.example.library.exception.ConflictException
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.DiscardBookTransactionsRequest
import com.example.library.models.DiscardBookTransactionsResponse
import com.example.library.repositories.DiscardBookTransactionsRepository
import com.example.library.repositories.LibraryBookInformationRepository
import com.example.library.services.DiscardBookTransactionsService
import com.example.library.utils.BookStatus
import org.springframework.stereotype.Service

@Service
class DiscardBookTransactionsServiceImpl(
    private val discardBookRepository: DiscardBookTransactionsRepository,
    private val libraryBookInformationRepository: LibraryBookInformationRepository
) : DiscardBookTransactionsService {

    override fun getAllDiscardBooks(): List<DiscardBookTransactions> =
        discardBookRepository.findAll()

    override fun getDiscardBookById(isbn: String): DiscardBookTransactions {
        return discardBookRepository.findByIsbnCode(isbn).orElseThrow {
            throw EntityNotFoundException("DiscardBook not found.")
        }
    }

    override fun postDiscardBook(discardBook: DiscardBookTransactionsRequest): DiscardBookTransactionsResponse {
        val existingDiscardBook = discardBookRepository.findByIsbnCode(discardBook.isbnCode)
        if (existingDiscardBook.isPresent)  throw ConflictException("Already has this id")

        val existingLibraryBook = libraryBookInformationRepository.findByIsbnCode(discardBook.isbnCode).orElseThrow()

        val addDiscardBook = DiscardBookTransactions(
            discardBookId = discardBook.id,
            bookId = discardBook.bookId,
            isbnCode = discardBook.isbnCode,
            name = discardBook.name,
            status = discardBook.status,
            description = discardBook.description,
            userId = discardBook.userId,
            createdBy = discardBook.createdBy,
            createdDateTime = discardBook.createdDateTime,
            updatedBy = discardBook.updatedBy,
            updatedDateTime = discardBook.updatedDateTime
        )

        existingLibraryBook.apply {
            this.status = BookStatus.DISCARD.name
        }

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
}
