package com.example.illo

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    // 애니메이션을 처리하기 위한 runnable 객체
    private val mRunnable : Runnable = Runnable {
        if (!isFinishing) { // 끝나지 않았을 때

            // 슬라이드업 애니메이션 실행
            slideUp(illo_logo, 500)
            fadeIn(illo_copy, 500)
            slideUp(illo_copy, 500)
            fadeIn(btn_login, 500)
            slideUp(btn_login, 500)
            fadeIn(btn_sign, 500)
            slideUp(btn_sign, 500)


            // 핸들러 이용해 1초 딜레이 후 토스트 메세지 실행
//            Handler().postDelayed({
//                Toast.makeText(this@SplashActivity, "1초가 지났습니다", Toast.LENGTH_LONG).show()
//            }, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 핸들러 이용해 0.5초 딜레이 후 mRunnable 실행
       Handler().postDelayed(mRunnable, 500)

        // 로그인 버튼
        btn_login.setOnClickListener {

        }

        // 가입 버튼
        btn_sign.setOnClickListener {

        }
    }

    // 위로 올리기
    fun slideUp(view : View, time : Int) {
        val animation = TranslateAnimation(0f, 0f, 0f, -300f )   // 애니메이션 인스턴스화
        animation.duration = time.toLong()  // 애니메이션 지속시간 설정
        animation.fillAfter = true  // 애니메이션 종료 후 상태 유지
        view.startAnimation(animation)  // 애니메이션 실행
    }

    // 페이드 인
    fun fadeIn(view: View, time: Int) {
        val anim = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.fade_in)    // 애니메이션 로드
        anim.duration = time.toLong()   // 애니메이션 지속시간
        anim.fillAfter = true   // 애니메이션 종료 후 상태 유지
        view.startAnimation(anim)   // 애니메이션 실행
    }
}