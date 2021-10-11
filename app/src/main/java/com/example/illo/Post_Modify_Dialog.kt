package com.example.illo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.dialog_post_modify.*

class Post_Modify_Dialog : DialogFragment() {
    lateinit var postActivity : PostActivity

    override fun onAttach(context: Context) {
        postActivity = activity as PostActivity
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

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
        val view: View = inflater.inflate(R.layout.dialog_post_modify, container, false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mentionList = arrayListOf<Mention>()
        mentionList.add(Mention("조유리"))
        mentionList.add(Mention("권은비"))
        mentionList.add(Mention("안유진"))

        // 수정하기 버튼 클릭 시
        val modifyButton = view.findViewById<TextView>(R.id.btn_modify)
        modifyButton.setOnClickListener {
            // 글쓰기 액티비티로 이동
            // 현재 게시글의 데이터 전달
            // Fragment --> Activity 이동
            activity?.let {
                val intent: Intent = Intent(activity, PostModifyActivity::class.java)
                // 이렇게 다 전달해줄 필요 없이 글 ID만 전달해줘도 되지 않을까???? 백엔드와 상의해보기
                intent.putExtra("원본 글", "어쩌고저쩌고 블라블라")
                intent.putExtra("해시태그","#이게해시태그다임마")
                intent.putExtra("멘션", mentionList)
                startActivity(intent)
            }
            dialog?.cancel()    // 다이얼로그 끝내기
        }

        // 삭제하기 버튼 클릭 시
        val deleteButton = view.findViewById<TextView>(R.id.btn_delete)
        deleteButton.setOnClickListener {
            postActivity.deleteAlertDialog()    // 액티비티의 삭제 다이얼로그 함수 호출
            dialog?.cancel()    // 다이얼로그 끝내기
        }
    }
}