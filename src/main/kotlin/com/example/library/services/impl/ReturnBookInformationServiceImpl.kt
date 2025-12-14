package com.example.library.services.impl

import com.example.library.entities.ReturnBookInformation
import com.example.library.exception.ConflictException
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.ReturnBookInformationRequest
import com.example.library.models.ReturnBookInformationResponse
import com.example.library.repositories.ReturnBookInformationRepository
import com.example.library.services.ReturnBookInformationService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ReturnBookInformationServiceImpl(
    private val returnBookRepository: ReturnBookInformationRepository,
) : ReturnBookInformationService {

    override fun getAllReturnBooks(): List<ReturnBookInformation> =
        returnBookRepository.findAll()

    override fun getReturnBookById(id: String): ReturnBookInformation =
        returnBookRepository.findById(id).orElseThrow {
            EntityNotFoundException("ReturnBook not found.")
        }

    override fun postReturnBook(returnBook: ReturnBookInformationRequest): ReturnBookInformationResponse {
        val existingBook = returnBookRepository.findByReturnBookId(returnBook.id)
        if (existingBook.isPresent) throw ConflictException("Already has this id")

        val entity = ReturnBookInformation(
            returnBookId = returnBook.id,
            isbnCode = returnBook.isbnCode,
            price = returnBook.price,
            bookId = returnBook.bookId,
            publisher = returnBook.publisher,
            name = returnBook.name,
            status = returnBook.status,
            amount = returnBook.amount,
            userId = returnBook.userId,
            createdBy = returnBook.createdBy,
            createdDateTime = returnBook.createdDateTime,
            updatedBy = returnBook.updatedBy,
            updatedDateTime = returnBook.updatedDateTime
        )

        returnBookRepository.save(entity)

        return returnBook.toResponse()
    }

    override fun putReturnBook(returnBook: ReturnBookInformationRequest): ReturnBookInformationResponse {

        val entity = returnBookRepository.findById(returnBook.id).orElseThrow {
            EntityNotFoundException("ReturnBook not found")
        }

        entity.apply {
            isbnCode = returnBook.isbnCode
            price = returnBook.price
            bookId = returnBook.bookId
            publisher = returnBook.publisher
            name = returnBook.name
            status = returnBook.status
            amount = returnBook.amount
            userId = returnBook.userId
            createdBy = returnBook.createdBy
            createdDateTime = returnBook.createdDateTime
            updatedBy = returnBook.updatedBy
            updatedDateTime = returnBook.updatedDateTime
        }

        returnBookRepository.saveAndFlush(entity)
        return returnBook.toResponse()
    }

    override fun deleteReturnBook(id: String): ResponseEntity<*> {
        val existingReturnBook = returnBookRepository.findByReturnBookId(id).orElseThrow {
            throw EntityNotFoundException("Entity not found")
        }
        returnBookRepository.deleteById(existingReturnBook.returnBookId)
        return ResponseEntity.accepted().body("")
    }

    private fun ReturnBookInformationRequest.toResponse() =
        ReturnBookInformationResponse(
            id, isbnCode, price, bookId, publisher, name,
            status, amount, userId,
            createdBy, createdDateTime, updatedBy, updatedDateTime
        )
}
