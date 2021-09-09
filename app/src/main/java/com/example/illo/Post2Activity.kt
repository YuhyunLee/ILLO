package com.example.illo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_post2.*

class Post2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post2)

        btn_post_modify.setOnClickListener{
//            Post2_Dialog_Modify_Fragment().show(supportFragmentManager, "Post2_Modify_Dialog")
            Post2_Delete_Dialog().show(supportFragmentManager, "Post2_Delete_Dialog")

        }


    }

//    public fun deleteAlertDialog() {
//        Post2_Dialog_Delete_Fragment().show(supportFragmentManager, "Post2_Delete_Dialog")
//    }

}