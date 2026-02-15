package com.example.library.services

import com.example.library.entities.LibraryBookInformation
import com.example.library.models.LibraryBookInformationRequest
import com.example.library.models.LibraryBookInformationResponse

interface LibraryBookInformationService {

    fun getAllLibraryBook(): List<LibraryBookInformation>;

    fun getLibraryBookByIsbnCode(isbn: String): LibraryBookInformation

    fun postLibraryBook(libraryBook: LibraryBookInformationRequest): LibraryBookInformationResponse

    fun putLibraryBook(libraryBook: LibraryBookInformationRequest): LibraryBookInformationResponse

    fun updateBookStatus(isbnCode: String, status: String): LibraryBookInformation

    fun getBookStatus(isbnCode: String, status: String): Int
}