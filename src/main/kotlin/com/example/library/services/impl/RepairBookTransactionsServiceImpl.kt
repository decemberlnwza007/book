package com.example.library.services.impl

import com.example.library.entities.RepairBookTransactions
import com.example.library.exception.ConflictException
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.RepairBookTransactionsRequest
import com.example.library.models.RepairBookTransactionsResponse
import com.example.library.repositories.RepairBookTransactionsRepository
import com.example.library.services.RepairBookTransactionsService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RepairBookTransactionsServiceImpl(
    private val repairBookRepository: RepairBookTransactionsRepository,
) : RepairBookTransactionsService {

    override fun getAllRepairBooks(): List<RepairBookTransactions> =
        repairBookRepository.findAll()

    override fun getRepairBookById(id: UUID): RepairBookTransactions {
        return repairBookRepository.findById(id).orElseThrow {
            EntityNotFoundException("RepairBook not found.")
        }
    }

    override fun postRepairBook(repairBook: RepairBookTransactionsRequest): RepairBookTransactionsResponse {
        val existingRepairBook = repairBookRepository.findByRepairBookId(repairBook.id)
        if (existingRepairBook.isPresent)  throw ConflictException("Already has this id")

        val addRepairBook = RepairBookTransactions(
            repairBookId = repairBook.id,
            bookId = repairBook.bookId,
            isbnCode = repairBook.isbnCode,
            name = repairBook.name,
            description = repairBook.description,
            userId = repairBook.userId,
            createdBy = repairBook.createdBy,
            createdDateTime = repairBook.createdDateTime,
            updatedBy = repairBook.updatedBy,
            updatedDateTime = repairBook.updatedDateTime
        )

        repairBookRepository.save(addRepairBook)

        return RepairBookTransactionsResponse(
            id = repairBook.id,
            bookId = repairBook.bookId,
            isbnCode = repairBook.isbnCode,
            name = repairBook.name,
            description = repairBook.description,
            userId = repairBook.userId,
            createdBy = repairBook.createdBy,
            createdDateTime = repairBook.createdDateTime,
            updatedBy = repairBook.updatedBy,
            updatedDateTime = repairBook.updatedDateTime
        )
    }

    override fun putRepairBook(repairBook: RepairBookTransactionsRequest): RepairBookTransactionsResponse {

        val existingRepairBook = repairBookRepository.findById(repairBook.id).orElseThrow {
            EntityNotFoundException("RepairBook not found.")
        }

        existingRepairBook.apply {
            bookId = repairBook.bookId
            isbnCode = repairBook.isbnCode
            name = repairBook.name
            description = repairBook.description
            userId = repairBook.userId
            createdBy = repairBook.createdBy
            createdDateTime = repairBook.createdDateTime
            updatedBy = repairBook.updatedBy
            updatedDateTime = repairBook.updatedDateTime

            repairBookRepository.saveAndFlush(this)

            return RepairBookTransactionsResponse(
                id = repairBook.id,
                bookId = repairBook.bookId,
                isbnCode = repairBook.isbnCode,
                name = repairBook.name,
                description = repairBook.description,
                userId = repairBook.userId,
                createdBy = repairBook.createdBy,
                createdDateTime = repairBook.createdDateTime,
                updatedBy = repairBook.updatedBy,
                updatedDateTime = repairBook.updatedDateTime
            )
        }
    }

    override fun deleteRepairBook(id: UUID): ResponseEntity<*> {
        val existingRepairBook = repairBookRepository.findByRepairBookId(id).orElseThrow {
            throw EntityNotFoundException("Entity not found")
        }

        repairBookRepository.deleteById(existingRepairBook.repairBookId)
        return ResponseEntity.accepted().body("")
    }
}
