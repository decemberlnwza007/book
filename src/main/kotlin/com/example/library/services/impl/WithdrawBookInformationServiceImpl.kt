package com.example.library.services.impl

import com.example.library.entities.WithdrawBookInformation
import com.example.library.exception.ConflictException
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.WithdrawBookInformationRequest
import com.example.library.models.WithdrawBookInformationResponse
import com.example.library.repositories.WithdrawBookInformationRepository
import com.example.library.services.WithdrawBookInformationService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class WithdrawBookInformationServiceImpl(
    private val withdrawBookRepository: WithdrawBookInformationRepository,
) : WithdrawBookInformationService {

    override fun getAllWithdrawBooks(): List<WithdrawBookInformation> =
        withdrawBookRepository.findAll()

    override fun getWithdrawBookByIsbnCode(isbn: String): WithdrawBookInformation =
        withdrawBookRepository.findByIsbnCode(isbn).orElseThrow {
            EntityNotFoundException("WithdrawBook not found.")
        }

    override fun postWithdrawBook(withdraw: WithdrawBookInformationRequest): WithdrawBookInformationResponse {
        val existingWithdrawBook = withdrawBookRepository.findByIsbnCode(withdraw.isbnCode)
        if (existingWithdrawBook.isPresent)  throw ConflictException("Already has this id")

        val entity = WithdrawBookInformation(
            withdrawId = withdraw.bookId,
            isbnCode = withdraw.isbnCode,
            bookId = withdraw.bookId,
            publisher = withdraw.publisher,
            name = withdraw.name,
            price = withdraw.price,
            status = withdraw.status,
            userId = withdraw.userId,
            amount = withdraw.amount,
            createdBy = withdraw.createdBy,
            createdDateTime = withdraw.createdDateTime,
            updatedBy = withdraw.updatedBy,
            updatedDateTime = withdraw.updatedDateTime
        )

        withdrawBookRepository.save(entity)

        return WithdrawBookInformationResponse (
            id = withdraw.bookId,
            isbnCode = withdraw.isbnCode,
            bookId = withdraw.bookId,
            publisher = withdraw.publisher,
            name = withdraw.name,
            price = withdraw.price,
            status = withdraw.status,
            userId = withdraw.userId,
            amount = withdraw.amount,
            createdBy = withdraw.createdBy,
            createdDateTime = withdraw.createdDateTime,
            updatedBy = withdraw.updatedBy,
            updatedDateTime = withdraw.updatedDateTime
        )
    }

    override fun putWithdrawBook(withdraw: WithdrawBookInformationRequest): WithdrawBookInformationResponse {
        val existingWithdrawBook = withdrawBookRepository.findByIsbnCode(withdraw.isbnCode)
        if (existingWithdrawBook.isEmpty) throw EntityNotFoundException("Entity not found")

        val entity = withdrawBookRepository.findById(withdraw.id).orElseThrow {
            EntityNotFoundException("WithdrawBook not found.")
        }

        entity.apply {
            isbnCode = withdraw.isbnCode
            bookId = withdraw.bookId
            publisher = withdraw.publisher
            name = withdraw.name
            price = withdraw.price
            status = withdraw.status
            userId = withdraw.userId
            amount = withdraw.amount
            createdBy = withdraw.createdBy
            createdDateTime = withdraw.createdDateTime
            updatedBy = withdraw.updatedBy
            updatedDateTime = withdraw.updatedDateTime
        }

        withdrawBookRepository.saveAndFlush(entity)

        return WithdrawBookInformationResponse(
            id = withdraw.id,
            isbnCode = withdraw.isbnCode,
            bookId = withdraw.bookId,
            publisher = withdraw.publisher,
            name = withdraw.name,
            price = withdraw.price,
            status = withdraw.status,
            userId = withdraw.userId,
            amount = withdraw.amount,
            createdBy = withdraw.createdBy,
            createdDateTime = withdraw.createdDateTime,
            updatedBy = withdraw.updatedBy,
            updatedDateTime = withdraw.updatedDateTime
        )
    }
}
