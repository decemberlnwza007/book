package com.example.library.repositories

import com.example.library.entities.RepairBookTransactions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface RepairBookTransactionsRepository : JpaRepository<RepairBookTransactions, UUID> {
    fun findByRepairBookId(id: UUID): Optional<RepairBookTransactions>
}