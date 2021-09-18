package com.example.illo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign0.*

class SignActivity0 : AppCompatActivity() {

    // 비밀번호 확인
    var isPasswordCorrect : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign0)


        // 가입하기 버튼 눌렀을 때
        btn_sign.setOnClickListener {

            // 이메일, 아이디, 비밀번호 받아오기
            val email : String = input_email.text.toString()
            val id : String = input_id.text.toString()
            val pass : String = input_pw1.text.toString()

            if (isPasswordCorrect)
            {
                // 새로운 계정 생성

            }
            else {
                Toast.makeText(
                baseContext, "이메일 또는 비밀번호를 올바르게 입력해주십시오.",
                Toast.LENGTH_SHORT
            ).show() }

        }

        // 이메일 입력받았을 때
        input_email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // 이미 있는 이메일이라면


                // 사용가능한 이메일이라면
            }
        })

        // 비밀번호 확인 입력받았을 때
        input_pw2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // 비밀번호 확인
                val pw1 = input_pw1.text.toString()
                val pw2 = s.toString()
                // 비밀번호가 일치한다면
                if (pw1 == pw2)
                {
                    isPasswordCorrect = true
                    // 비밀번호가 올바르다는 문구가 보이게
                    txt_correct_pw.visibility = View.VISIBLE
                    txt_error_pw.visibility = View.INVISIBLE
                }
                // 일치하지 않는다면
                else
                {
                    isPasswordCorrect = false
                    // 비밀번호가 틀렸다는 문구가 보이게
                    txt_error_pw.visibility = View.VISIBLE
                    txt_correct_pw.visibility = View.INVISIBLE
                }
            }
        })

    }

}