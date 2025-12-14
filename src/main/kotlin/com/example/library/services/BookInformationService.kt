package com.example.library.services

import com.example.library.entities.BookInformation
import com.example.library.models.BookInformationRequest
import com.example.library.models.BookInformationResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

interface BookInformationService {
    fun getAllBooks() : List<BookInformation>

    fun getBookById(id: String) : BookInformation

    fun postBook(book: BookInformationRequest) : BookInformationResponse

    fun putBook(book: BookInformationRequest): BookInformationResponse

    fun deleteBook(id: String) : ResponseEntity<*>
}