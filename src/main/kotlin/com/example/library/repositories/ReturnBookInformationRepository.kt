package com.example.library.repositories

import com.example.library.entities.ReturnBookInformation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ReturnBookInformationRepository : JpaRepository<ReturnBookInformation, String> {
    fun findByReturnBookId(id: String): Optional<ReturnBookInformation>
}