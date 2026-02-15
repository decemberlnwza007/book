package com.example.library.repositories

import com.example.library.entities.DiscardBookTransactions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.Optional
import java.util.UUID

@Repository
interface DiscardBookTransactionsRepository : JpaRepository<DiscardBookTransactions, UUID> {
    fun findByIsbnCode(isbn: String): Optional<DiscardBookTransactions>

    @Query("""
        SELECT dbt FROM DiscardBookTransactions dbt WHERE dbt isbnCode = :isbnCode AND dbt.studentId = studentId AND date(dbt.updatedDatetime) = :localDate
    """, nativeQuery = true)
    fun findByIsbnCodeAndStudentIdAndUpdatedDateTime(
        @Param("isbnCode") isbnCode: String,
        @Param("studentId") studentId: String,
        @Param("localDate") localDate: LocalDate
    ): Optional<DiscardBookTransactions?>
}