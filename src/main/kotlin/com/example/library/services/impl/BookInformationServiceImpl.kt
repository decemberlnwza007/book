package com.example.library.services.impl

import com.example.library.entities.BookInformation
import com.example.library.exception.ConflictException
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.BookInformationRequest
import com.example.library.models.BookInformationResponse
import com.example.library.repositories.BookInformationRepository
import com.example.library.services.BookInformationService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.server.ServerErrorException
import java.time.LocalDateTime
import java.util.UUID

@Service
class BookInformationServiceImpl(
    private val bookRepository: BookInformationRepository
) : BookInformationService {
    override fun getAllBooks(): List<BookInformationResponse> {
        val books = bookRepository.findAll()

        val bookResponse = mutableListOf<BookInformationResponse>()
        books.forEach { book ->
            val bookInformationResponse = BookInformationResponse(
                isbnCode = book.isbnCode,
                writeBy = book.writeBy,
                name = book.name,
                publisher = book.publisher,
                categoryId = book.categoryId,
                amount = book.amount,
                price = book.price
            )
            bookResponse.add(bookInformationResponse)
        }
        LOGGER.info("$bookResponse")
        return bookResponse
    }

    override fun getBookByIsbnCode(isbn: String): BookInformationResponse {
        val book = bookRepository.findByIsbnCode(isbn).orElseThrow {
            throw EntityNotFoundException("Book not found.")
        }
        return BookInformationResponse (
            isbnCode = book.isbnCode,
            writeBy = book.writeBy,
            name = book.name,
            publisher = book.publisher,
            categoryId = book.categoryId,
            amount = book.amount,
            price = book.price
        )

    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(
            BookInformationServiceImpl::class.java
        )
    }

    override fun postBook(book: BookInformationRequest): BookInformationResponse {
        val existingBook = bookRepository.findByIsbnCode(book.isbnCode)
        if (existingBook.isPresent)  throw ConflictException("Already has this isbn code")

        val addBook = try {
            BookInformation(
                bookId = UUID.randomUUID(),
                isbnCode = book.isbnCode,
                writeBy = book.writeBy,
                name = book.name,
                publisher = book.publisher,
                categoryId = book.categoryId,
                amount = book.amount,
                price = book.price,
                createdBy = book.createdBy,
                createdDateTime = LocalDateTime.now(),
                updatedBy = book.updatedBy,
                updatedDateTime = LocalDateTime.now()
            )
        } catch (e: Exception) {
            LOGGER.error("Exception", e)
            throw Exception("Exception", e)
        } catch (e: ServerErrorException) {
            LOGGER.error("ServerErrorException", e)
            throw ServerErrorException("ServerErrorException", e)
        } catch (e: HttpClientErrorException) {
            LOGGER.error("HttpClientErrorException: ${e.message}", e)
            throw Exception("")
        }

        bookRepository.save(addBook)

        return BookInformationResponse(
            isbnCode = book.isbnCode,
            writeBy = book.writeBy,
            name = book.name,
            publisher = book.publisher,
            categoryId = book.categoryId,
            amount = book.amount,
            price = book.price
        )
    }

    override fun putBook(book: BookInformationRequest): BookInformationResponse {
        val existingBook = bookRepository.findByIsbnCode(book.isbnCode)
        if (existingBook.isEmpty) throw EntityNotFoundException("Entity not found")

        existingBook.get().apply {
            isbnCode = book.isbnCode
            writeBy = book.writeBy
            name = book.name
            publisher = book.publisher
            categoryId = book.categoryId
            amount = book.amount
            price = book.price
            createdBy = book.createdBy
            createdDateTime = LocalDateTime.now()
            updatedBy = book.updatedBy
            updatedDateTime = LocalDateTime.now()

            bookRepository.saveAndFlush(existingBook.get())

            return BookInformationResponse(
                isbnCode = book.isbnCode,
                writeBy = book.writeBy,
                name = book.name,
                publisher = book.publisher,
                categoryId = book.categoryId,
                amount = book.amount,
                price = book.price
            )
        }
    }

    override fun deleteBook(isbn: String): ResponseEntity<*> {
        val existingBook = bookRepository.findByIsbnCode(isbn).get()

        bookRepository.deleteById(existingBook.bookId)
        return ResponseEntity.accepted().body("")
    }

}