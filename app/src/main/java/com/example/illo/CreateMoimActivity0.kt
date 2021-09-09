package com.example.illo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_alarm.*
import kotlinx.android.synthetic.main.activity_create_moim0.*
import kotlinx.android.synthetic.main.fragment_home1_0.*

class CreateMoimActivity0 : AppCompatActivity() {
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
            finish()
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)
        }

        // 설정 버튼
        btn_set.setOnClickListener {
            // 모임 데이터 생성
            moimList.add(Moim(R.drawable.illust_3_fingers, "연경신","배구 황제 김연경"))
//            recyclerview_moim_card.getAdapter()?.notifyDataSetChanged()

            // Intent 생성 (모임 만들기0 -> 모임 만들기1)
            val intent : Intent = Intent(this@CreateMoimActivity0, CreateMoimActivity1::class.java)
            startActivity(intent)

            finish()
        }
    }
}