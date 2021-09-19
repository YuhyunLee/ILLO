package com.example.illo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_alarm.*
import kotlinx.android.synthetic.main.activity_create_moim0.*
import kotlinx.android.synthetic.main.fragment_home1_0.*

class CreateMoimActivity0 : AppCompatActivity() {

    lateinit var moimName : String
    lateinit var moimIntro : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_moim0)

        // 툴바로 세팅
        setSupportActionBar(toolbar_create_moim)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_to_left);    // 뒤로 가기 버튼 커스텀
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로 가기 버튼 자동 생성

        // 뒤로 가기 버튼 설정
        toolbar_create_moim.setNavigationOnClickListener {
            finish()    // 액태비티 끝내기
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)   // 애니메이션
        }

        // 모임 이름 입력 받기
        input_moim_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // 입력받은 모임 이름 저장
                moimName = s.toString()
            }
        })

        // 모임 소개글 입력 받기
        input_moim_intro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // 입력받은 모임 소개글 저장
                moimIntro = s.toString()
            }
        })

        // 설정 버튼
        btn_set.setOnClickListener {
            // *----서버/DB 필요----*
            // 모임 데이터 생성


            // Intent 생성 (모임 만들기0 -> 모임 만들기1)
            val intent : Intent = Intent(this@CreateMoimActivity0, CreateMoimActivity1::class.java)
            startActivity(intent)

            finish()
        }
    }
}