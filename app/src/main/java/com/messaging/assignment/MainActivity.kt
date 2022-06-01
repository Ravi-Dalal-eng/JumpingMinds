package com.messaging.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.messaging.assignment.adapter.QuoteAdapter
import com.messaging.assignment.databinding.ActivityMainBinding
import com.messaging.assignment.viewmodel.MainViewModel
import com.messaging.assignment.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager=LinearLayoutManager(this)

        val repository = (application as QuoteApplication).quoteRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            if(it.results.isEmpty()){
                //This code will execute only for first time as if there is no internet connection
                Toast.makeText(this,"Check your internet connection",Toast.LENGTH_LONG).show()
            }
            else {
                binding.recyclerView.adapter = QuoteAdapter(it.results)
            }
        })
    }
}