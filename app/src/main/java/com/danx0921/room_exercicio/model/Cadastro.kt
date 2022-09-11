package com.danx0921.room_exercicio.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Cadastro(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val Username: String,
    @ColumnInfo val Password: String,
    @ColumnInfo(name = "created_date") val createdDate: Date = Date()


)
