package com.example.library.repositories

import com.example.library.entities.BookInformation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface BookInformationRepository : JpaRepository<BookInformation, UUID> {
    fun findByIsbnCode(isbnCode: String) : Optional<BookInformation>
    fun deleteByBookId(bookId: UUID)
}