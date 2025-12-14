package com.example.library.repositories

import com.example.library.entities.DiscardBookTransactions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.Repository
import java.util.Optional
import java.util.UUID

interface DiscardBookTransactionsRepository : JpaRepository<DiscardBookTransactions, UUID> {
    fun findByDiscardBookId(id: UUID): Optional<DiscardBookTransactions>
}