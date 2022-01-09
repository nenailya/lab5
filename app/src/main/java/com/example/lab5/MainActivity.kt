package com.example.lab5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.databinding.ActivityMainBinding
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity() {
    private val viewModel = ViewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageView: ImageView = findViewById(R.id.imageView)
        val download: Button = findViewById(R.id.download)
        download.setOnClickListener {
            viewModel.download()
        }
        viewModel.bitmap.observe(this) {
            imageView.setImageBitmap(it)
        }
    }
}

