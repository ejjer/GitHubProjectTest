package com.example.githubprojecttest.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class MainActivityViewModel : ViewModel() {
    val inputUserName = MutableLiveData<String>()
}