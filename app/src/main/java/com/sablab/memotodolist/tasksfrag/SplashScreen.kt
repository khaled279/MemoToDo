package com.sablab.memotodolist.tasksfrag

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sablab.memotodolist.MainActivity
import com.sablab.memotodolist.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Thread(Runnable {
            Thread.sleep(2000)
            runOnUiThread(Runnable {
                startActivity(Intent(applicationContext , MainActivity::class.java))
            })
        }).start()
    }
}