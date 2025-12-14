package com.example.library.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    @ResponseBody
    fun entityNotFoundException(ex: EntityNotFoundException) : ResponseEntity<ErrorDetails> {
        return ResponseEntity<ErrorDetails>(ErrorDetails(HttpStatus.NO_CONTENT.value(), message = ex.message),
            HttpStatus.NO_CONTENT)
    }

    @ExceptionHandler(ConflictException::class)
    @ResponseBody
    fun conflictException(ex: ConflictException) : ResponseEntity<ErrorDetails> {
        return ResponseEntity<ErrorDetails>(ErrorDetails(HttpStatus.CONFLICT.value(), message = ex.message),
            HttpStatus.CONFLICT)
    }

    data class ErrorDetails(val status: Int, val message: String?)
}