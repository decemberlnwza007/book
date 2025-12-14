package com.example.library.services

import com.example.library.entities.ReturnBookInformation
import com.example.library.models.ReturnBookInformationRequest
import com.example.library.models.ReturnBookInformationResponse
import org.springframework.http.ResponseEntity

interface ReturnBookInformationService {

    fun getAllReturnBooks(): List<ReturnBookInformation>

    fun getReturnBookById(id: String): ReturnBookInformation

    fun postReturnBook(returnBook: ReturnBookInformationRequest): ReturnBookInformationResponse

    fun putReturnBook(returnBook: ReturnBookInformationRequest): ReturnBookInformationResponse

    fun deleteReturnBook(id: String): ResponseEntity<*>
}
