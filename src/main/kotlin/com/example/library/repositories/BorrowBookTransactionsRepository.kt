package com.example.library.repositories

import com.example.library.entities.BorrowBookTransactions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Optional
import java.util.UUID

@Repository
interface BorrowBookTransactionsRepository : JpaRepository<BorrowBookTransactions, UUID> {
//    fun findByBorrowIsbnCode(isbnCode: String): Optional<BorrowBookTransactions>

    fun findByIsbnCode(isbnCode: String): Optional<BorrowBookTransactions>

    @Query("""
        SELECT bbt.isbnCode FROM BorrowBookTransactions bbt WHERE bbt.isbnCode = :isbnCode AND bbt.studentId = :studentId AND date(bbt.updatedDateTime) = :localDate
    """)
    fun findByIsbnCodeAndStudentIdAndUpdatedDateTime(
       isbnCode: String,
       studentId: String,
       localDate: LocalDate
    ): String?

    @Query("""
        SELECT bbt FROM BorrowBookTransactions bbt WHERE bbt.isbnCode = :isbnCode AND bbt.studentId = :studentId AND date(bbt.updatedDateTime) = :localDate
    """)
    fun findByIsbnCodeAndStudentIdAndUpdatedDateTime1(
        isbnCode: String,
        studentId: String,
        localDate: LocalDate
    ): Optional<BorrowBookTransactions>
}