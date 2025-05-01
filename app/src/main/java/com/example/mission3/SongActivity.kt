package com.example.mission3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mission3.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    // 뷰 바인딩 객체 선언
    lateinit var binding: ActivitySongBinding // import가 아니라 xml 에 activitySong 없어서
    lateinit var song: Song
    lateinit var timer : Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 초기화
        binding = ActivitySongBinding.inflate(layoutInflater)
        // xml에 있는 뷰를 가져와서 설정
        setContentView(binding.root)
        initSong()
        setPlayer(song)
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
        // 전달받은 데이터가 존재하는지 먼저 확인 -> null 반환 방지
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

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
    }
    private fun initSong() {
        if(intent.hasExtra("title")&&intent.hasExtra("singer")){
            song = Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second", 0),
                intent.getIntExtra("playTime", 0),
                intent.getBooleanExtra("isPlaying", false)
            )
        }
        startTimer()
    }

    private fun setPlayer(song: Song) {
        binding.songMusicTitleTv.text = intent.getStringExtra("title")!!
        binding.songSingerNameTv.text = intent.getStringExtra("singer")!!
        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.second % 60)
        binding.songProgressSb.progress = (song.second * 1000 / song.playTime)
    }
    private fun initBottomNavigation() {
        TODO("Not yet implemented")
    }


private fun SongActivity.setPlayerStatus(isPlaying: kotlin.Boolean) {
    song.isPlaying = isPlaying
    timer.isPlaying = isPlaying

    if (isPlaying) {
        binding.songMiniplayerIv.visibility = View.VISIBLE
        binding.songPauseIv.visibility = View.GONE
    } else {
        binding.songMiniplayerIv.visibility = View.GONE
        binding.songPauseIv.visibility = View.VISIBLE
    }
}
    private fun startTimer() {
        timer = Timer(song.playTime, song.isPlaying)
        timer.start()
    }
    inner class Timer(private val playTime: Int, var isPlaying: Boolean = true):Thread() {
        private var second : Int = 0
        private var mills: Float = 0f

        override fun run() {
            super.run()
            try {
                while (true) {
                    if(second >= playTime){
                        break
                    }

                    if(isPlaying) {
                        sleep(50)
                        mills += 50

                        runOnUiThread {
                            binding.songProgressSb.progress = ((mills/playTime)*100).toInt()
                        }
                        if(mills%1000== 0f) {
                            runOnUiThread {
                                binding.songStartTimeTv.text = String.format("%02d:%02d", second / 60, second % 60)
                            }
                            second++
                        }
                    }
                }
            }catch(e: InterruptedException) {
                Log.d("song", "스레드가 죽었습니다. ${e.message}")
            }

        }
    }

}