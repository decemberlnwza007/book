package com.example.library.services

import com.example.library.entities.UserInformation
import com.example.library.models.UserInformationRequest
import com.example.library.models.UserInformationResponse
import org.springframework.http.ResponseEntity

interface UserInformationService {

    fun getAllUsers() : List<UserInformation>

    fun getUser(id: String) : UserInformation

    fun postUser(user: UserInformationRequest) : UserInformationResponse

    fun putUser(user: UserInformationRequest) : UserInformationResponse

    fun deleteUser(id: String) : ResponseEntity<*>
}