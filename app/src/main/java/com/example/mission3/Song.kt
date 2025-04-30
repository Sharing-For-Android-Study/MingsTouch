package com.example.mission3

//제목, 가수, 사진, 재생시간, 현재 재생시간, isplaying
data class Song(
    val title : String = "",
    val singer : String = "",
    var second : Int = 0, // 여기부터 변경 가능
    var playTime: Int = 0,
    var isPlaying: Boolean = false
)