package com.example.illo

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 밑줄 긋기
        goto_signup.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        goto_find.paintFlags = Paint.UNDERLINE_TEXT_FLAG

    }
}