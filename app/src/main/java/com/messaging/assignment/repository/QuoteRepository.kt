package com.messaging.assignment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.messaging.assignment.api.QuoteService
import com.messaging.assignment.database.QuoteDatabase
import com.messaging.assignment.models.QuoteList
import com.messaging.assignment.utils.CheckNetwork

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int){

        if(CheckNetwork.isInternetAvailable(applicationContext)){
            //This code will execute if there is an internet connection
            val result = quoteService.getQuotes(page)
            if(result?.body() != null){
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        }
        else{
            //This code will execute if there is no internet connection
            val quotes = quoteDatabase.quoteDao().getQuotes()
            // Just for now all values are dummy except quotes
            val quoteList = QuoteList(1,1,1,quotes, 1, 1)
            quotesLiveData.postValue(quoteList)
        }

    }
}