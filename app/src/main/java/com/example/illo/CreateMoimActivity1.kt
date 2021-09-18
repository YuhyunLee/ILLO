package com.example.illo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import kotlinx.android.synthetic.main.activity_create_moim1.*

class CreateMoimActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_moim1)

        // 일러스트 애니메이션
        scaleUp(illust_thumb, 500)

        // 툴바로 세팅
        setSupportActionBar(toolbar_create_moim)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_to_left);    // 뒤로 가기 버튼 커스텀
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로 가기 버튼 자동 생성

        // 뒤로 가기 버튼 설정
        toolbar_create_moim.setNavigationOnClickListener {
            finish()    // 액티비티 끝내기
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)
        }

        // 우리 모임 둘러보기 버튼 설정
        btn_to_moim.setOnClickListener {
            // 모임 만들기 1 --> 홈2
            val intent : Intent = Intent(this@CreateMoimActivity1, Home2Activity::class.java)
            startActivity(intent)
        }
    }

    fun scaleUp(view: View, time: Int) {
        val anim = ScaleAnimation(0f,1.0f,
            0f,1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)    // 애니메이션 로드
        anim.duration = time.toLong()   // 애니메이션 지속시간
        view.startAnimation(anim)   // 애니메이션 실행
    }
}