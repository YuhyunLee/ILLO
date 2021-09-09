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
    private lateinit var auth: FirebaseAuth

    // 비밀번호 확인
    var isPasswordCorrect : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign0)

        auth = Firebase.auth

        // 가입하기 버튼 눌렀을 때
        btn_sign.setOnClickListener {

            val email : String = input_email.text.toString()
            val pass : String = input_pw1.text.toString()

            if (isPasswordCorrect)
            {
                // 새로운 계정 생성
               createAccount(email, pass)
            }
            else {
                Toast.makeText(
                baseContext, "이메일 또는 비밀번호를 올바르게 입력해주십시오.",
                Toast.LENGTH_SHORT
            ).show() }

        }

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
                    txt_correct_pw.visibility = View.VISIBLE    // 보이게
                    txt_error_pw.visibility = View.INVISIBLE    // 안 보이게
                }
                // 일치하지 않는다면
                else
                {
                    isPasswordCorrect = false
                    txt_error_pw.visibility = View.VISIBLE      // 보이게
                    txt_correct_pw.visibility = View.INVISIBLE  // 안 보이게
                }
            }
        })

    }

    public override fun onStart() {
        super.onStart()
        // 사용자가 현재 로그인 되어 있는지 확인
        val currentUser = auth.currentUser
        if(currentUser != null){    // 이미 로그인되어있다면
            reload();
        }
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "계정 생성에 실패했습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun reload() {

    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}