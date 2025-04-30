package com.example.mission3


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mission3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        //
        binding.homeAlbumImgIv1.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, AlbumFragment().apply {
                    arguments = Bundle().apply {
                        putString("title", "IU 5th Album 'LILAC'")
                        putString("singer", "아이유(IU)")
                        putInt("coverImg", R.drawable.img_album_exp3)
                    }
                })
                .commitAllowingStateLoss()
        }

        return binding.root
    }
}