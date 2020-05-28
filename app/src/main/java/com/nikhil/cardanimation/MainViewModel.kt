package com.nikhil.cardanimation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var isFront  = MutableLiveData<Boolean>().apply { false };
}