package com.example.library.exception

import org.springframework.web.bind.annotation.ControllerAdvice

class ConflictException(message: String) : RuntimeException(message){

}