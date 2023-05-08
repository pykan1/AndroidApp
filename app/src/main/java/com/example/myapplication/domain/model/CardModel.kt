package com.example.myapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.R
import java.util.*

@Entity(tableName = "card")
data class CardModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val imageId: Int = R.drawable.math,
    val title: String = "",
    val body: String = "",
    var formula: String = "",
    val arrayhint: String = ""
) {
    override fun equals(other: Any?): Boolean {
        other as CardModel
        return other.body == body &&
                other.title == title &&
                other.formula == formula &&
                other.arrayhint == arrayhint
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageId
        result = 31 * result + title.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + formula.hashCode()
        result = 31 * result + arrayhint.hashCode()
        return result
    }
}