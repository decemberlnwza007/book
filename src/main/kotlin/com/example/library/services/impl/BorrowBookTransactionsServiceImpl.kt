package com.example.library.services.impl

import com.example.library.entities.BorrowBookTransactions
import com.example.library.exception.ConflictException
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.BorrowBookTransactionsRequest
import com.example.library.models.BorrowBookTransactionsResponse
import com.example.library.repositories.BorrowBookTransactionsRepository
import com.example.library.repositories.ReturnBookInformationRepository
import com.example.library.services.BorrowBookTransactionsService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BorrowBookTransactionsServiceImpl(
    private val borrowBookRepository: BorrowBookTransactionsRepository,
) : BorrowBookTransactionsService {
    override fun getAllBorrowBooks(): List<BorrowBookTransactions> = borrowBookRepository.findAll()

    override fun getBorrowBook(id: UUID): BorrowBookTransactions {
        return borrowBookRepository.findByBorrowBookId(id).orElseThrow {
            throw EntityNotFoundException("BorrowBook not found.")
        }
    }

    override fun postBorrowBook(borrow: BorrowBookTransactionsRequest): BorrowBookTransactionsResponse {
        val existingBorrowBook = borrowBookRepository.findByBorrowBookId(borrow.id)
        if (existingBorrowBook.isPresent)  throw ConflictException("Already has this id")

        val addBorrowBook = BorrowBookTransactions(
            borrowBookId = borrow.id,
            isbnCode = borrow.isbnCode,
            studentId = borrow.studentId,
            bookId = borrow.bookId,
            name = borrow.name,
            status = borrow.status,
            price = borrow.price,
            borrowDate = borrow.borrowDate,
            returnDate = borrow.returnDate,
            createdBy = borrow.createdBy,
            createdDateTime = borrow.createdDateTime,
            updatedBy = borrow.updatedBy,
            updatedDateTime = borrow.updatedDateTime,
            userId = borrow.userId
        )

        borrowBookRepository.save(addBorrowBook)

        return BorrowBookTransactionsResponse(
            id = borrow.id,
            isbnCode = borrow.isbnCode,
            studentId = borrow.studentId,
            bookId = borrow.bookId,
            name = borrow.name,
            status = borrow.status,
            price = borrow.price,
            borrowDate = borrow.borrowDate,
            returnDate = borrow.returnDate,
            createdBy = borrow.createdBy,
            createdDateTime = borrow.createdDateTime,
            updatedBy = borrow.updatedBy,
            updatedDateTime = borrow.updatedDateTime,
            userId = borrow.userId
        )
    }

    override fun putBorrowBook(borrow: BorrowBookTransactionsRequest): BorrowBookTransactionsResponse {
        val existingBorrowBook = borrowBookRepository.findByBorrowBookId(borrow.id)
        if (existingBorrowBook.isEmpty) throw EntityNotFoundException("Entity not found")

        existingBorrowBook.get().apply {
            bookId = borrow.bookId
            isbnCode = borrow.isbnCode
            studentId = borrow.studentId
            name = borrow.name
            status = borrow.status
            price = borrow.price
            borrowDate = borrow.borrowDate
            returnDate = borrow.returnDate
            createdBy = borrow.createdBy
            createdDateTime = borrow.createdDateTime
            updatedBy = borrow.updatedBy
            updatedDateTime = borrow.updatedDateTime
            userId = borrow.userId

            borrowBookRepository.saveAndFlush(existingBorrowBook.get())

            return BorrowBookTransactionsResponse(
                id = borrow.id,
                isbnCode = borrow.isbnCode,
                bookId = borrow.bookId,
                studentId = borrow.studentId,
                name = borrow.name,
                status = borrow.status,
                price = borrow.price,
                borrowDate = borrow.borrowDate,
                returnDate = borrow.returnDate,
                createdBy = borrow.createdBy,
                createdDateTime = borrow.createdDateTime,
                updatedBy = borrow.updatedBy,
                updatedDateTime = borrow.updatedDateTime,
                userId = borrow.userId
            )
        }
    }

    override fun deleteBorrowBook(id: UUID): ResponseEntity<*> {
        val existingBorrowBook = borrowBookRepository.findByBorrowBookId(id).orElseThrow {
            throw EntityNotFoundException("Entity not found")
        }

        borrowBookRepository.deleteById(existingBorrowBook.borrowBookId)

        return ResponseEntity.accepted().body("")
    }

}