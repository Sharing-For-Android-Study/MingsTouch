package com.example.mission3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.mission3.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
/*
1. handler를 이용한 스플래시 화면 구현
2. 시작 테마 style 로 지정
- 앱 로드 후라 추천
 */
class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstancestate: Bundle?) {
        super.onCreate(savedInstancestate)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 1000)
    }
}