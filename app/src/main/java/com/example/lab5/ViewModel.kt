package com.example.lab5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService

class ViewModel : ViewModel() {
    var bitmap: MutableLiveData<Bitmap> = MutableLiveData()
    private val url = URL("https://thiscatdoesnotexist.com/")
    private val executor: ExecutorService = App.executor

    fun download() {
        executor.submit {
            Thread.sleep(1000)
            Log.d("myT", "${Thread.currentThread()}")
            bitmap.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }
}