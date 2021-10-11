package com.example.illo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_post_modify_step1.*

class PostModify_Step1_Fragment : Fragment() {
    lateinit var img_recyclerView: RecyclerView // 이미지 리사이클러뷰

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_post_modify_step1, container, false)

        // 서버에서 데이터 가져오기
        // 원본 게시글의 텍스트, 이미지, 멘션 등 가져오기
        val text_data = arguments?.getString("원본 글")
        val mentionList_data = arguments?.getSerializable("멘션")
        // 텍스트 프래그먼트로 전달
        val postmodifyStep1TextFragment = PostModify_Step1_Text_Fragment()
        val bundle = Bundle()
        bundle.putString("원본 글", text_data)
        bundle.putSerializable("멘션", mentionList_data)
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.text_modify_post, postmodifyStep1TextFragment)
            .commit()

        createImageData()   // ((임의로 데이터 생성))

        // 이미지 리사이클러뷰 생성
        img_recyclerView = view.findViewById(R.id.recyclerview_img_modify_post)
        img_recyclerView.setHasFixedSize(true)
        img_recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        img_recyclerView.adapter = PostImageAdapter(img_data)

        // Fab 버튼 설정
        FabSetting()

        return view

    }


    fun FabSetting() {
        // 플로팅 버튼 클릭시 에니메이션 동작 기능
        val addMediaFab = view?.findViewById<ImageView>(R.id.modify_fab_add_media)
        addMediaFab?.setOnClickListener {
            Toast.makeText(activity, "미디어 추가 버튼 클릭!", Toast.LENGTH_SHORT).show()
            OpenFab()
        }

        // 게임 버튼 클릭 이벤트
        val gameFab = view?.findViewById<ImageView>(R.id.modify_fab_game)
        gameFab?.setOnClickListener {
            Toast.makeText(activity, "게임 버튼 클릭!", Toast.LENGTH_SHORT).show()
            CloseFab()
        }

        // 갤러리 버튼 클릭 이벤트
        val galleryFab = view?.findViewById<ImageView>(R.id.modify_fab_gallery)
        galleryFab?.setOnClickListener {
            Toast.makeText(activity, "갤러리 버튼 클릭!", Toast.LENGTH_SHORT).show()
            CloseFab()
        }

        // 배경 아무데나 클릭 시
        val backgroundFade = view?.findViewById<ImageView>(R.id.modify_fade_background)
        backgroundFade?.setOnClickListener {
            CloseFab()
        }
    }

    /***
     *  플로팅 액션 버튼 클릭시 동작하는 애니메이션 효과 세팅
     */

    // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션 세팅
    private fun OpenFab() {
        modify_fade_background.visibility = View.VISIBLE
        modify_fab_game.bringToFront()

        ObjectAnimator.ofFloat(modify_fab_game, "translationY", 0f).apply { start() }
        ObjectAnimator.ofFloat(modify_fab_gallery, "translationY", -200f).apply { start() }
    }

    // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션 세팅
    private fun CloseFab() {
        modify_fade_background.visibility = View.INVISIBLE
        modify_fab_add_media.bringToFront()

        ObjectAnimator.ofFloat(modify_fab_game, "translationY", 0f).apply { start() }
        ObjectAnimator.ofFloat(modify_fab_gallery, "translationY", 0f).apply { start() }
    }

    // 이미지 데이터 생성하는 함수
    fun createImageData() {
        img_data.add(PostImage(R.drawable.cat))
        img_data.add(PostImage(R.drawable.wallet))
    }
}