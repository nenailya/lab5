package com.example.lab5

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object App : Application() {
    val executor: ExecutorService = Executors.newSingleThreadExecutor()
}