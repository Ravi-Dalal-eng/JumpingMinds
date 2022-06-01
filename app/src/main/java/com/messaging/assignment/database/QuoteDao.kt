package com.messaging.assignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.messaging.assignment.models.Quote

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<Quote>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes() : List<Quote>
}