package com.messaging.assignment

import android.app.Application
import com.messaging.assignment.api.QuoteService
import com.messaging.assignment.api.RetrofitHelper
import com.messaging.assignment.database.QuoteDatabase
import com.messaging.assignment.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)
    }
}