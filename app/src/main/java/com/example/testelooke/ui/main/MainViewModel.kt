package com.example.testelooke.ui.main

import androidx.lifecycle.*
import com.example.testelooke.data.model.Donuts
import com.example.testelooke.data.model.Topping
import com.example.testelooke.data.repository.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val _donutsLiveData = MutableLiveData<Donuts>()
    val donutsLiveData: LiveData<Donuts>
        get() = _donutsLiveData

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    init {
        getDonuts()
    }

    fun getDonuts(){
        viewModelScope.launch {
            _donutsLiveData.postValue(Repository.getDonuts())
        }
    }

    fun getToppingList(donuts: Donuts): List<Topping>{
        val completeToppingList = arrayListOf<Topping>()
        donuts.forEach{
            it.topping.forEach {
                completeToppingList.add(it)
            }
        }
        return completeToppingList.distinct().toMutableList()
    }
}