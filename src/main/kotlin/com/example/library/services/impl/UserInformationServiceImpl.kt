package com.example.library.services.impl

import com.example.library.entities.UserInformation
import com.example.library.exception.ConflictException
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.UserInformationRequest
import com.example.library.models.UserInformationResponse
import com.example.library.repositories.UserInformationRepository
import com.example.library.services.UserInformationService
import com.example.library.utils.GeneratedAlphabetId
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserInformationServiceImpl (
    private val userRepository: UserInformationRepository,
): UserInformationService {
    override fun getAllUsers(): List<UserInformation> = userRepository.findAll()

    override fun getUser(id: String): UserInformation {
        return userRepository.findByUsername("test").orElseThrow {
            throw EntityNotFoundException("User not found.")
        }
    }

    override fun postUser(user: UserInformationRequest): UserInformationResponse {

        val existingUser = userRepository.findByUsername(user.username)
        if (existingUser.isPresent) throw ConflictException("Already has this username")

        val maxId = userRepository.findAllIdDesc()

        val addUser = UserInformation (
            userId = GeneratedAlphabetId.generateId(maxId),
            username = user.username,
            password = user.password,
            status = user.status,
            permission = user.permission,
            permissionLevel = user.permission_level,
            createdBy = user.createdBy,
            createdDateTime = user.createdDateTime!!,
            updatedBy = user.updatedBy,
            updatedDateTime = user.updatedDateTime!!
        )

        userRepository.save(addUser)

        return UserInformationResponse (
            username = user.username,
            password = user.password,
            status = user.status,
            permission = user.permission,
            permission_level = user.permission_level,
            createdBy = user.createdBy,
            createdDateTime = user.createdDateTime!!,
            updatedBy = user.updatedBy,
            updatedDateTime = user.updatedDateTime!!
        )
    }

    override fun putUser(user: UserInformationRequest): UserInformationResponse {
        val existingUser = userRepository.findByUsername(user.username)
        if (existingUser.isEmpty) throw EntityNotFoundException("Entity not found")

        existingUser.get().apply {
            username = user.username
            password = user.password
            status = user.status
            permission = user.permission
            permissionLevel = user.permission_level
            createdBy = user.createdBy
            createdDateTime = user.createdDateTime!!
            updatedBy = user.updatedBy
            updatedDateTime = user.updatedDateTime!!

            userRepository.saveAndFlush(existingUser.get())

            return UserInformationResponse (
                username = user.username,
                password = user.password,
                status = user.status,
                permission = user.permission,
                permission_level = user.permission_level,
                createdBy = user.createdBy,
                createdDateTime = user.createdDateTime,
                updatedBy = user.updatedBy,
                updatedDateTime = user.updatedDateTime
            )
        }
    }

    override fun deleteUser(id: String): ResponseEntity<*> {
        val existingUser = userRepository.findByUsername("test").orElseThrow {
            throw EntityNotFoundException("Entity not found")
        }

        userRepository.deleteById(existingUser.userId)

        return ResponseEntity.accepted().body("")
    }
}