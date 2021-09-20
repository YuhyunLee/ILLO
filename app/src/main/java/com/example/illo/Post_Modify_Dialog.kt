package com.example.illo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_post2_modify.*

class Post_Modify_Dialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_post2_modify, container, false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 수정하기 버튼 클릭 시
        btn_modify.setOnClickListener {

        }

        // 삭제하기 버튼 클릭 시
        btn_delete.setOnClickListener {
//
//            val deleteDialogFragment = Post2_Dialog_Delete_Fragment()
//            deleteDialogFragment.setTargetFragment(this, 0)
//            deleteDialogFragment.show(requireFragmentManager(), "Post2_Delete_Dialog")
        }

    }
}