package com.example.library.services

import com.example.library.entities.CategoryMaster
import com.example.library.models.CategoryMasterRequest
import com.example.library.models.CategoryMasterResponse
import jdk.jfr.Category
import org.springframework.http.ResponseEntity

interface CategoryMasterService {

    fun getAllCategories(): List<CategoryMaster>

    fun getCategoryById(id: String) : CategoryMaster

    fun postCategory(category: CategoryMasterRequest): CategoryMasterResponse

    fun putCategory(category: CategoryMasterRequest): CategoryMasterResponse

    fun deleteCategory(id: String) : ResponseEntity<*>
}