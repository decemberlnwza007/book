package com.example.library.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user_information", schema = "book")
data class UserInformation (

    @Id
    @Column(name = "id")
    var userId: String,

    @Column(name = "username")
    var username: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "status")
    var status: String,

    @Column(name = "permission")
    var permission: String,

    @Column(name = "permission_level")
    var permissionLevel: String,

    @Column(name = "created_by")
    var createdBy: String,

    @Column(name = "created_datetime")
    var createdDateTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_by")
    var updatedBy: String,

    @Column(name = "updated_datetime")
    var updatedDateTime: LocalDateTime = LocalDateTime.now()
)