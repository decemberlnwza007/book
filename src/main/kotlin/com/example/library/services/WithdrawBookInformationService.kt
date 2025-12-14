package com.example.library.services

import com.example.library.entities.WithdrawBookInformation
import com.example.library.models.WithdrawBookInformationRequest
import com.example.library.models.WithdrawBookInformationResponse
import org.springframework.http.ResponseEntity

interface WithdrawBookInformationService {

    fun getAllWithdrawBooks(): List<WithdrawBookInformation>

    fun getWithdrawBookById(id: String): WithdrawBookInformation

    fun postWithdrawBook(withdraw: WithdrawBookInformationRequest): WithdrawBookInformationResponse

    fun putWithdrawBook(withdraw: WithdrawBookInformationRequest): WithdrawBookInformationResponse

    fun deleteWithdrawBook(id: String): ResponseEntity<*>
}
