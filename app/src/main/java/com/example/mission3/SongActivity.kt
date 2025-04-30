package com.example.mission3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mission3.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    // 뷰 바인딩 객체 선언
    lateinit var binding: ActivitySongBinding // import가 아니라 xml 에 activitySong 없어서

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 초기화
        binding = ActivitySongBinding.inflate(layoutInflater)
        // xml에 있는 뷰를 가져와서 설정
        setContentView(binding.root)
/*
        binding.mainPlayerCl.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java)) // SongActivity로 이동
        }
        initBottomNavigation()
*/
        /*
        // MainActivity에서 보낸 데이터 받기
        val title = intent.getStringExtra("title")
        val singer = intent.getStringExtra("singer")

        binding.songTitleTv.text = title
        binding.songSingerTv.text = singer
*/
        // 전달받은 데이터가 존재하는지 먼저 확인
        if(intent.hasExtra("title")&&intent.hasExtra("singer")){
            binding.songMusicTitleTv.text = intent.getStringExtra("title")!!
            binding.songSingerNameTv.text = intent.getStringExtra("singer")!!
        }
        binding.songDownIb.setOnClickListener {
            finish() //activity 꺼주기
        }
        binding.songMiniplayerIv.setOnClickListener { 
            // 어떤 작업인지 가독성있게 알려주는 함수
            setPlayerStatus(false)
        }
        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
        }
    }

    private fun initBottomNavigation() {
        TODO("Not yet implemented")
    }
/*
    private fun initSong() {
        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            binding.songMusicTitleTv.text = intent.getStringExtra("title")
            binding.songSingerNameTv.text = intent.getStringExtra("singer")
        }
    }

 */
}

private fun SongActivity.setPlayerStatus(isPlaying: kotlin.Boolean) {
    if(isPlaying) {
        binding.songMiniplayerIv.visibility = View.VISIBLE
        binding.songPauseIv.visibility = View.GONE
    }
    else {
        binding.songMiniplayerIv.visibility = View.GONE
        binding.songPauseIv.visibility = View.VISIBLE
    }
}
