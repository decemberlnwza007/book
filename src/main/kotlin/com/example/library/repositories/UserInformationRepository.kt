package com.example.library.repositories

import com.example.library.entities.UserInformation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserInformationRepository : JpaRepository<UserInformation, String> {
    fun findByUsername(username: String) : Optional<UserInformation>

    @Query("""
        select ui.id from UserInformation ui
        order by ui.id desc
        limit 1
    """)
    fun findAllIdDesc() : String
}