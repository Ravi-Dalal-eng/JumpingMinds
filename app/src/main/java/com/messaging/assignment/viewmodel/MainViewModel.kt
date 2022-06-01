package com.messaging.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messaging.assignment.models.QuoteList
import com.messaging.assignment.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            // Just for now I gave static value as 1
            repository.getQuotes(1)
        }
    }

    val quotes : LiveData<QuoteList>
        get() = repository.quotes
}