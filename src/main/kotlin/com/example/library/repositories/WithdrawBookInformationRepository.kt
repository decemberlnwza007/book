package com.example.library.repositories

import com.example.library.entities.WithdrawBookInformation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface WithdrawBookInformationRepository : JpaRepository<WithdrawBookInformation, String> {
    fun findByIsbnCode(isbn: String): Optional<WithdrawBookInformation>
}