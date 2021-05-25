package com.example.dicodingjetpackpro.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_example")
data class ExampleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)