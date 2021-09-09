package com.example.illo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_post2_delete.*


class Post2_Delete_Dialog : DialogFragment() {

//    lateinit var deleteListener : Post2_Dialog_Delete_Listener
//
//    public interface Post2_Dialog_Delete_Listener {
//        public fun deleteCallback(isDelete : Boolean)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 전체화면 스타일 설정
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_post2_delete, container, false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 수정하기 버튼 클릭 시
        btn_cancel.setOnClickListener {

        }

        // 삭제하기 버튼 클릭 시
        btn_delete.setOnClickListener {

        }

    }
}