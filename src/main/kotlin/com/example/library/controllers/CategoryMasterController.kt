package com.example.library.controllers

import com.example.library.entities.CategoryMaster
import com.example.library.models.CategoryMasterRequest
import com.example.library.models.CategoryMasterResponse
import com.example.library.services.CategoryMasterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryMasterController (
    private val categoryMasterService: CategoryMasterService,
) {

    @GetMapping("/categories")
    fun getCategories(): List<CategoryMaster> = categoryMasterService.getAllCategories()

    @GetMapping("/category")
    fun getCategory(
        @RequestParam("id") id: String
    ) : CategoryMaster = categoryMasterService.getCategoryById(id)

    @PostMapping("/category")
    fun postCategory (
        @RequestBody categoryMasterRequest: CategoryMasterRequest
    ) : CategoryMasterResponse = categoryMasterService.postCategory(categoryMasterRequest)

    @PutMapping("/category")
    fun putCategory (
        @RequestBody categoryMasterRequest: CategoryMasterRequest
    ) : CategoryMasterResponse = categoryMasterService.putCategory(categoryMasterRequest)

    @DeleteMapping("/category")
    fun deleteUser(
        @RequestParam id: String
    ) : ResponseEntity<*> = categoryMasterService.deleteCategory(id)
}