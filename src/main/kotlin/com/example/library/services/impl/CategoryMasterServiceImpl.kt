package com.example.library.services.impl

import com.example.library.entities.CategoryMaster
import com.example.library.exception.ConflictException
import com.example.library.exception.EntityNotFoundException
import com.example.library.models.CategoryMasterRequest
import com.example.library.models.CategoryMasterResponse
import com.example.library.repositories.CategoryMasterRepository
import com.example.library.services.CategoryMasterService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CategoryMasterServiceImpl (
    private val categoryRepository: CategoryMasterRepository,
): CategoryMasterService {
    override fun getAllCategories(): List<CategoryMaster> = categoryRepository.findAll()

    override fun getCategoryById(id: String): CategoryMaster {
        return categoryRepository.findByCategoryId(id).orElseThrow {
            throw EntityNotFoundException("Category not found.")
        }
    }

    override fun postCategory(category: CategoryMasterRequest): CategoryMasterResponse {
        val existingCategory = categoryRepository.findByCategoryId(category.id)
        if (existingCategory.isPresent)  throw ConflictException("Already has this id")

        val addCategory = CategoryMaster (
            categoryId = category.id,
            name = category.name,
            createdBy = category.createdBy,
            createdDateTime = category.createdDateTime,
            updatedBy = category.updatedBy,
            updatedDateTime = category.updatedDateTime
        )

        categoryRepository.save(addCategory)

        return CategoryMasterResponse (
            id = category.id,
            name = category.name,
            createdBy = category.createdBy,
            createdDateTime = category.createdDateTime,
            updatedBy = category.updatedBy,
            updatedDateTime = category.updatedDateTime
        )
    }

    override fun putCategory(category: CategoryMasterRequest): CategoryMasterResponse {
        val existingCategory = categoryRepository.findByCategoryId(category.id)
        if (existingCategory.isEmpty) throw EntityNotFoundException("Entity not found")

        existingCategory.get().apply {
            name = category.name
            createdBy = category.createdBy
            createdDateTime = category.createdDateTime
            updatedBy = category.updatedBy
            updatedDateTime = category.updatedDateTime

            categoryRepository.saveAndFlush(existingCategory.get())

            return CategoryMasterResponse (
                id = category.id,
                name = category.name,
                createdBy = category.createdBy,
                createdDateTime = category.createdDateTime,
                updatedBy = category.updatedBy,
                updatedDateTime = category.updatedDateTime
            )
        }
    }

    override fun deleteCategory(id: String): ResponseEntity<*> {
        val existingCategory = categoryRepository.findByCategoryId(id).orElseThrow {
            throw EntityNotFoundException("Entity not found")
        }

        categoryRepository.deleteById(existingCategory.categoryId)

        return ResponseEntity.accepted().body("")
    }

}