package com.example.library.repositories

import com.example.library.entities.BorrowBookTransactions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface BorrowBookTransactionsRepository : JpaRepository<BorrowBookTransactions, UUID> {
    fun findByBorrowBookId(id: UUID): Optional<BorrowBookTransactions>
}