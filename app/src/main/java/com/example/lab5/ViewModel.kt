package com.example.lab5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ViewModel : ViewModel() {
    var bitmap: MutableLiveData<Bitmap> = MutableLiveData()
    private val url = URL("https://thiscatdoesnotexist.com/")
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    fun download() {
        executor.submit {
            Thread.sleep(1000)
            Log.d("myT", "${Thread.currentThread()}")
            bitmap.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }

    override fun onCleared() {
        Log.d("myExecutor", "shtdwn")
        executor.shutdown()
        super.onCleared()
    }
}