package com.example.library.controllers

import com.example.library.entities.LibraryBookInformation
import com.example.library.services.LibraryBookInformationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LibraryBookController(
    private val libraryBookInformationService: LibraryBookInformationService
) {

    @GetMapping("/library")
    fun getBookStatusAmount(
        @RequestParam("isbn_code") isbnCode: String,
        @RequestParam("status") status: String
    ): Int = libraryBookInformationService.getBookStatus(isbnCode, status)



    @PutMapping("/library")
    fun updateBookStatus(
        @RequestParam("isbn_code") isbnCode: String,
        @RequestParam("status") status: String
    ): LibraryBookInformation = libraryBookInformationService.updateBookStatus(isbnCode, status)
}