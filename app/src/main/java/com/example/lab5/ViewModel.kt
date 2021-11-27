package com.example.lab5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ViewModel : ViewModel() {
    var bitmap: MutableLiveData<Bitmap> = MutableLiveData()
    private val url = URL("https://thiscatdoesnotexist.com/")

    fun download() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            bitmap.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }
}