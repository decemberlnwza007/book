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

@Service
class BookInformationServiceImpl(
    private val bookRepository: BookInformationRepository,
) : BookInformationService {
    override fun getAllBooks(): List<BookInformation> = bookRepository.findAll()
    override fun getBookById(id: String): BookInformation {
        TODO("Not yet implemented")
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(
            BookInformationServiceImpl::class.java
        )
    }

    override fun postBook(book: BookInformationRequest): BookInformationResponse {
        val existingBook = bookRepository.findByBookId(book.id)
        if (existingBook.isPresent)  throw ConflictException("Already has this id")

        val addBook = try {
            BookInformation(
                bookId = book.id,
                isbnCode = book.isbnCode,
                writeBy = book.writeBy,
                name = book.name,
                publisher = book.publisher,
                categoryId = book.categoryId,
                amount = book.amount,
                price = book.price,
                createdBy = book.createdBy,
                createdDateTime = book.createdDateTime,
                updatedBy = book.updatedBy,
                updatedDateTime = book.updatedDateTime
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
            price = book.price,
            createdBy = book.createdBy,
            createdDateTime = book.createdDateTime,
            updatedBy = book.updatedBy,
            updatedDateTime = book.updatedDateTime
        )
    }

    override fun putBook(book: BookInformationRequest): BookInformationResponse {
        val existingBook = bookRepository.findByBookId(book.id)
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
            createdDateTime = book.createdDateTime
            updatedBy = book.updatedBy
            updatedDateTime = book.updatedDateTime

            bookRepository.saveAndFlush(existingBook.get())

            return BookInformationResponse(
                isbnCode = book.isbnCode,
                writeBy = book.writeBy,
                name = book.name,
                publisher = book.publisher,
                categoryId = book.categoryId,
                amount = book.amount,
                price = book.price,
                createdBy = book.createdBy,
                createdDateTime = book.createdDateTime,
                updatedBy = book.updatedBy,
                updatedDateTime = book.updatedDateTime
            )
        }
    }

    override fun deleteBook(id: String): ResponseEntity<*> {
        val existingBook = bookRepository.findByBookId(id)

        bookRepository.deleteById(id)
        return ResponseEntity.accepted().body("")
    }

}