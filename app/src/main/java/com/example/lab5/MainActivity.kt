package com.example.lab5

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.databinding.ActivityMainBinding
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData

class MainActivity : AppCompatActivity() {
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private val img = "https://thiscatdoesnotexist.com/"
    private var bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) bitmap.value =
            savedInstanceState.getParcelable("bitmap")

        binding.download.setOnClickListener {
            download(URL(img))
        }
        bitmap.observe(this) {
            binding.imageView.setImageBitmap(it)
        }
        log("onCreate")
    }

    private fun download(url: URL) {
        executor.execute {
            log("sleeping")
            Thread.sleep(1000)
            log("downloading in ${Thread.currentThread()}")

            bitmap.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("bitmap", bitmap.value)
    }

    override fun onStop() {
        executor.shutdown()
        super.onStop()
        log("onStop")
    }

    private val tag = "myTag"

    private fun log(msg: String) {
        Log.d(tag, msg)
    }

}

