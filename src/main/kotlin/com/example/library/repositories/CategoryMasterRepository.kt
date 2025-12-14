package com.example.library.repositories

import com.example.library.entities.CategoryMaster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CategoryMasterRepository : JpaRepository<CategoryMaster, String> {
    fun findByCategoryId(id: String) : Optional<CategoryMaster>
}