package com.example.testelooke.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testelooke.data.model.Donuts
import com.example.testelooke.data.model.Topping
import com.example.testelooke.data.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _donutsLiveData = MutableLiveData<Donuts>()
    val donutsLiveData: LiveData<Donuts>
        get() = _donutsLiveData

    private val _responseError = MutableLiveData<String>()
    val responseError: LiveData<String>
        get() = _responseError

    init {
        getDonuts()

    }

    fun getDonuts() {
        viewModelScope.launch {
            val response = Repository.getDonuts()
            if (response.isSuccessful) {
                _donutsLiveData.postValue(response.body())
            } else {
                _responseError.postValue(response.message())
            }
        }
    }

    fun setToppingList(donuts: Donuts): MutableList<Topping> {
        val completeToppingList = arrayListOf<Topping>()
        donuts.forEach {
            it.topping.forEach {
                completeToppingList.add(it)
            }
        }
        completeToppingList.sortBy { it.id }
        return completeToppingList.distinct().toMutableList()
    }
}