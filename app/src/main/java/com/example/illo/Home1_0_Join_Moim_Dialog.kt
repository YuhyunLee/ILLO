package com.example.illo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_home1_0_join_moim.*

class Home1_0_Join_Moim_Dialog : DialogFragment() {

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
        val view: View = inflater.inflate(R.layout.dialog_home1_0_join_moim, container, false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이미지 테두리 입히기
        moim_join_card.clipToOutline = true

        // 가입하기 버튼 클릭 시
        btn_join.setOnClickListener {
            Log.d("join_moim", "가입 완료!")
            // *----서버/DB 필요----*

            // 해당 모임에 유저 데이터 저장
            // 내 모임에 추가
        }

        // 취소하기 버튼 클릭 시
        btn_cancel.setOnClickListener {
            // 다이얼로그 끄기
            dialog?.cancel()
        }

    }

    fun getInstance(): Home1_0_Join_Moim_Dialog {
        return Home1_0_Join_Moim_Dialog()
    }
}