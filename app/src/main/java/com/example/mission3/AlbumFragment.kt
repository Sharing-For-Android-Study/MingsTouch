package com.example.mission3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mission3.databinding.FragmentAlbumBinding

class AlbumFragment : Fragment() {

    lateinit var binding: FragmentAlbumBinding

    // fragment에서는 oncreateVIEW
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumBinding.inflate(inflater, container, false) // inflater와 다름

        binding.albumSongImgIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm, AlbumFragment())
                .commitAllowingStateLoss()
        }
        /*
        // 전달받은 데이터 처리
        val albumTitle = arguments?.getString("title")
        val albumSinger = arguments?.getString("singer")
        val coverImg = arguments?.getInt("coverImg")

        binding.albumTitleTv.text = albumTitle
        binding.albumSingerTv.text = albumSinger
        if (coverImg != null) {
            binding.albumSongImgIv.setImageResource(coverImg)
        }

         */
        return binding.root
    }
}
