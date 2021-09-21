package com.example.illo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_write_post.*

class WritePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_post)

        // 툴바 설정
        toolbarSetting()

        // 1단계 <--> 2단계 이동

    }

    // 툴바 세팅
    fun toolbarSetting() {
        // 툴바로 세팅
        setSupportActionBar(toolbar_write_post)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_to_left);    // 뒤로 가기 버튼 커스텀
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로 가기 버튼 자동 생성

        // 뒤로가기 버튼 설정
        toolbar_write_post.setNavigationOnClickListener {
            finish()    // 액티비티 끝내기
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)   // 애니메이션
        }


    }
}