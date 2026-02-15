package com.example.library.repositories

import com.example.library.entities.LibraryBookInformation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface LibraryBookInformationRepository: JpaRepository<LibraryBookInformation, UUID> {
    fun findByIsbnCode(isbn: String?): Optional<LibraryBookInformation>


    @Query("""
        select count(lb.status) from LibraryBookInformation lb where isbnCode = :isbnCode and status = :status
    """)
    fun     countStatus(isbnCode: String, status: String): Int


}