package com.example.expensetracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val label: String,
    val amount: Double,
    val description: String) : Serializable {

    fun isIncome(): Boolean = this.amount >= 0

    fun formattedAmount(): String {
        val prefix = if (isIncome()) "+" else "-"
        return "$prefix $%.2f".format(Math.abs(this.amount))
    }

    fun getColorResource(defaultGreen: Int = R.color.green, defaultRed: Int = R.color.red): Int {
        return if (isIncome()) defaultGreen else defaultRed
    }
}