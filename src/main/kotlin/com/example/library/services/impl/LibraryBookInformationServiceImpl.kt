package com.example.library.services.impl

import com.example.library.entities.LibraryBookInformation
import com.example.library.exception.ConflictException
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.LibraryBookInformationRequest
import com.example.library.models.LibraryBookInformationResponse
import com.example.library.repositories.LibraryBookInformationRepository
import com.example.library.services.LibraryBookInformationService
import org.springframework.stereotype.Service

@Service
class LibraryBookInformationServiceImpl (
    private val libraryBookInformationRepository: LibraryBookInformationRepository,
): LibraryBookInformationService {
    override fun getAllLibraryBook(): List<LibraryBookInformation> = libraryBookInformationRepository.findAll()

    override fun getLibraryBookByIsbnCode(isbn: String): LibraryBookInformation {
        return libraryBookInformationRepository.findByIsbnCode(isbn).orElseThrow {
            throw EntityNotFoundException("Library Book not found")
        }
    }

    override fun postLibraryBook(libraryBook: LibraryBookInformationRequest): LibraryBookInformationResponse {
        val existingLibraryBook = libraryBookInformationRepository.findByIsbnCode(libraryBook.isbnCode)
        if (existingLibraryBook.isPresent) throw ConflictException("Already has this isbn code")

        val addLibraryBook = LibraryBookInformation(
            id = libraryBook.id,
            bookId = libraryBook.bookId,
            isbnCode = libraryBook.isbnCode,
            status = libraryBook.status,
            createdBy = libraryBook.createdBy,
            updatedBy = libraryBook.updatedBy,
            updatedDatetime = libraryBook.updatedDatetime,
            createdDatetime = libraryBook.createdDatetime
        )

        libraryBookInformationRepository.save(addLibraryBook)

        return LibraryBookInformationResponse(
            bookId = libraryBook.bookId,
            isbnCode = libraryBook.isbnCode,
            bookName = libraryBook.bookName,
            status = libraryBook.status,
            createdBy = libraryBook.createdBy,
            createdDatetime = libraryBook.createdDatetime,
            updatedBy = libraryBook.updatedBy,
            updatedDatetime = libraryBook.updatedDatetime,
        )
    }

    override fun putLibraryBook(libraryBook: LibraryBookInformationRequest): LibraryBookInformationResponse {
        TODO("Not yet implemented")
    }

    override fun updateBookStatus(
        isbnCode: String,
        status: String
    ): LibraryBookInformation {
        val existingBookStatus = libraryBookInformationRepository.findByIsbnCode(isbnCode).orElseThrow {
            EntityNotFoundException("Book status not found")
        }


        existingBookStatus.apply {
            this.status = status

            libraryBookInformationRepository.saveAndFlush(this)

            return LibraryBookInformation(
                status = this.status,
                id = this.id,
                bookId = this.bookId,
                isbnCode = this.isbnCode,
                createdBy = this.createdBy,
                updatedBy = this.updatedBy,
                updatedDatetime = this.updatedDatetime,
                createdDatetime = this.createdDatetime
            )
        }
    }

    override fun getBookStatus(isbnCode: String, status: String): Int {
        return libraryBookInformationRepository.countStatus(isbnCode, status)
    }
}