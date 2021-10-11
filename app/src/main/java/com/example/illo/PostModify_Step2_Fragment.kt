package com.example.illo

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment

class PostModify_Step2_Fragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_post_modify_step2, container, false)

        // 서버에서 데이터 가져오기
        val hashTag_data = arguments?.getString("해시태그")

        // 해시태그에 원래 데이터 넣어주기
        val modifyHashTag = view.findViewById<EditText>(R.id.modify_hashtag)
        modifyHashTag.setText(hashTag_data)

        return view
    }

    // Activity의 OnCreate와 같은 역할을 하는 함수
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("life_cycle", "F onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

}

