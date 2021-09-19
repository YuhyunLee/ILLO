package com.example.illo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_edit_moim_info0.*

class EditMoimInfoActivity0 : AppCompatActivity() {

    lateinit var newName : String   // 변경할 모임 이름
    lateinit var newIntro : String  // 변경할 소개글

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_moim_info0)

        // 툴바로 세팅
        setSupportActionBar(toolbar_edit_moim_info)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_to_left);    // 뒤로 가기 버튼 커스텀
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로 가기 버튼 자동 생성

        // 뒤로가기 버튼 설정
        toolbar_edit_moim_info.setNavigationOnClickListener {
            finish()    // 액티비티 끝내기
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)   // 애니메이션
        }

        // 모임 이름 입력 구현
        new_moim_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // 입력받은 모임 이름 저장
                newName = s.toString()
            }
        })

        new_moim_intro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // 입력받은 모임 이름 저장
                newIntro = s.toString()
            }
        })

        // 변경 버튼 구현
        btn_modify.setOnClickListener {
            // *----서버/DB 필요----*
            // 해당 모임의 정보 수정

            // Intent 생성 (모임 정보 수정0 -> 모임 정보 수정1)
            val intent : Intent = Intent(this@EditMoimInfoActivity0, EditMoimInfoActivity1::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)   // 애니메이션

            finish()    // 액티비티 끝내기
        }
    }
}