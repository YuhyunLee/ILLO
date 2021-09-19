package com.example.illo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import kotlinx.android.synthetic.main.activity_edit_moim_info1.*

class EditMoimInfoActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_moim_info1)

        // 일러스트 애니메이션
        scaleUp(illust_thumb, 500)


        // 툴바로 세팅
        setSupportActionBar(toolbar_edit_moim_info)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_to_left);    // 뒤로 가기 버튼 커스텀
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로 가기 버튼 자동 생성

        // 뒤로 가기 버튼 설정
        toolbar_edit_moim_info.setNavigationOnClickListener {
            finish()    // 액티비티 끝내기
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)
        }

        // 홈으로 가기 버튼
        btn_to_home2.setOnClickListener {
            // 어차피 홈2에서 불러냈으니 그냥 액티비티 끝내면 됨
            finish()    // 액티비티 끝내기
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)
        }
    }

    // 커지는 애니메이션
    fun scaleUp(view: View, time: Int) {
        val anim = ScaleAnimation(0f,1.0f,
            0f,1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)    // 애니메이션 로드
        anim.duration = time.toLong()   // 애니메이션 지속시간
        view.startAnimation(anim)   // 애니메이션 실행
    }
}