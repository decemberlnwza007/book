package com.example.library.services.impl

import com.example.library.entities.BorrowBookTransactions
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.BorrowBookTransactionsRequest
import com.example.library.models.BorrowBookTransactionsResponse
import com.example.library.repositories.BorrowBookTransactionsRepository
import com.example.library.repositories.CategoryMasterRepository
import com.example.library.repositories.LibraryBookInformationRepository
import com.example.library.services.BorrowBookTransactionsService
import com.example.library.utils.BookStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.server.ServerErrorException
import java.lang.Exception
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Optional
import java.util.UUID
import kotlin.jvm.Throws

@Service
class BorrowBookTransactionsServiceImpl(
    private val borrowBookRepository: BorrowBookTransactionsRepository,
    private val libraryBookRepository: LibraryBookInformationRepository
) : BorrowBookTransactionsService {
    override fun getAllBorrowBooks(): List<BorrowBookTransactions> = borrowBookRepository.findAll()

    companion object {
        private val LOGGER = LoggerFactory.getLogger(BorrowBookTransactionsServiceImpl::class.java)
    }

    override fun getBorrowBook(isbn: String): BorrowBookTransactionsResponse {
        val borrow = borrowBookRepository.findByIsbnCode(isbn).orElseThrow {
            throw EntityNotFoundException("BorrowBook not found.")
        }

        return BorrowBookTransactionsResponse(
            isbnCode = borrow.isbnCode,
            studentId = borrow.studentId,
            bookId = borrow.bookId,
            name = borrow.name,
            status = borrow.status,
            price = borrow.price,
            borrowDate = borrow.borrowDate,
            returnDate = borrow.returnDate,
            userId = borrow.userId,
        )

    }

    override fun postBorrowBook(borrow: BorrowBookTransactionsRequest): BorrowBookTransactionsResponse {

        LOGGER.info("isbn_code: ${borrow.isbnCode} and studentId: ${borrow.studentId}")

        val existingLibraryBook = libraryBookRepository.findByIsbnCode(borrow.isbnCode).orElseThrow {
            throw EntityNotFoundException("not found")
        }

        val existingBorrowBook = borrowBookRepository.findByIsbnCodeAndStudentIdAndUpdatedDateTime(
            borrow.isbnCode!!,
            borrow.studentId!!,
            localDate = LocalDate.now()
        )
        LOGGER.info("localdatetime : ${LocalDate.now()}")

        if (existingBorrowBook.isNullOrEmpty()) {
            val addBorrowBook = BorrowBookTransactions(
                borrowBookId = UUID.randomUUID(),
                isbnCode = borrow.isbnCode,
                studentId = borrow.studentId,
                bookId = borrow.bookId,
                name = borrow.name,
                status = borrow.status,
                price = borrow.price!!,
                borrowDate = borrow.borrowDate!!,
                returnDate = borrow.returnDate!!,
                createdBy = "SYSTEM",
                createdDateTime = LocalDateTime.now(),
                updatedBy =  "SYSTEM",
                updatedDateTime = LocalDateTime.now(),
                userId = borrow.userId!!
            )

            existingLibraryBook.apply {
                this.status = BookStatus.BORROWED.name
            }

            try {
                borrowBookRepository.saveAndFlush(addBorrowBook)
                libraryBookRepository.save(existingLibraryBook)

            } catch (e: Exception) {
                LOGGER.error("Exception", e)
                throw Exception("Exception", e)
            } catch (e: ServerErrorException) {
                LOGGER.error("ServerErrorException", e)
                throw ServerErrorException("ServerErrorException", e)
            } catch (e: HttpClientErrorException) {
                LOGGER.error("HttpClientErrorException: ${e.message}", e)
                throw Exception(e.message)
            }


            return BorrowBookTransactionsResponse(
                isbnCode = borrow.isbnCode,
                studentId = borrow.studentId,
                bookId = borrow.bookId,
                name = borrow.name,
                status = BookStatus.BORROWED.name,
                price = borrow.price,
                borrowDate = borrow.borrowDate,
                returnDate = borrow.returnDate,
                createdBy = borrow.createdBy,
                createdDateTime = LocalDateTime.now(),
                updatedBy = borrow.updatedBy,
                updatedDateTime = LocalDateTime.now(),
                userId = borrow.userId
            )
        } else {
            throw EntityNotFoundException("1")
        }


    }

    override fun putBorrowBook(borrow: BorrowBookTransactionsRequest): BorrowBookTransactionsResponse {
        LOGGER.info("isbnCode: ${borrow.isbnCode} and studentId: ${borrow.studentId}")
        val existingBorrowBook = borrowBookRepository.findByIsbnCodeAndStudentIdAndUpdatedDateTime1(
            borrow.isbnCode!!,
            borrow.studentId!!,
            LocalDate.now()
        )
        if (existingBorrowBook.isEmpty) throw EntityNotFoundException("Entity not found")

        existingBorrowBook.get().apply {
            bookId = borrow.bookId
            isbnCode = borrow.isbnCode
            studentId = borrow.studentId
            name = borrow.name
            status = borrow.status
            price = borrow.price!!
            borrowDate = borrow.borrowDate!!
            returnDate = borrow.returnDate!!
            updatedBy = borrow.updatedBy!!
            updatedDateTime = LocalDateTime.now()
            userId = borrow.userId!!
        }

        borrowBookRepository.saveAndFlush(existingBorrowBook.get())

        return BorrowBookTransactionsResponse(
            isbnCode = borrow.isbnCode,
            bookId = borrow.bookId,
            studentId = borrow.studentId,
            name = borrow.name,
            status = borrow.status,
            price = borrow.price,
            borrowDate = borrow.borrowDate,
            returnDate = borrow.returnDate,
            userId = borrow.userId
        )
    }
}