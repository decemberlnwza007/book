package com.example.library.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "category_master", schema = "book")
data class CategoryMaster (

    @Id
    @Column(name = "id")
    var categoryId: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "created_by")
    var createdBy: String,

    @Column(name = "created_datetime")
    var createdDateTime: LocalDateTime?,

    @Column(name = "updated_by")
    var updatedBy: String,

    @Column(name = "updated_datetime")
    var updatedDateTime: LocalDateTime?,
)
