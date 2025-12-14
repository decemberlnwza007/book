package com.example.library.controllers

import com.example.library.entities.UserInformation
import com.example.library.models.UserInformationRequest
import com.example.library.models.UserInformationResponse
import com.example.library.services.UserInformationService
import jakarta.transaction.Transactional
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserInformationController (
    private val userInformationService: UserInformationService,
) {

    @GetMapping("/users")
    fun getUsers() : List<UserInformation> = userInformationService.getAllUsers()

    @GetMapping("/user")
    fun getUser(@RequestParam("id") id : String) : UserInformation = userInformationService.getUser(id)

    @PostMapping("/user")
    @Transactional
    fun postUser(
        @RequestBody userInformationRequest: UserInformationRequest
    ) : UserInformationResponse = userInformationService.postUser(userInformationRequest)

    @PutMapping("/user")
    @Transactional
    fun putUser(
        @RequestBody userInformationRequest: UserInformationRequest
    ) : UserInformationResponse = userInformationService.putUser(userInformationRequest)

    @DeleteMapping("/user")
    @Transactional
    fun deleteUser(@RequestParam id : String) : ResponseEntity<*> = userInformationService.deleteUser(id)
}