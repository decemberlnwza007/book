package com.example.library.repositories

import com.example.library.entities.BookInformation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface BookInformationRepository : JpaRepository<BookInformation, String> {
    @Query("select bi from BookInformation bi where bi.id = :id")
    fun findByBookId(id: String) : Optional<BookInformation>
}