package com.example.illo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_post_modify_step1_text.*

class PostModify_Step1_Text_Fragment : Fragment() {
    lateinit var mention_recyclerView: RecyclerView    // 멘션 리사이클러뷰
    var mention_data = arrayListOf<Mention>()  // 멘션 데이터
    lateinit var modified_data : String      // 수정한 글 데이터

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_post_modify_step1_text, container, false)

        // 멘션 리사이클러뷰 설정
        mention_recyclerView = view.findViewById(R.id.recyclerview_mention_modify_post)
        mention_recyclerView.setHasFixedSize(true)
        mention_recyclerView.layoutManager = GridLayoutManager(activity, 7)
        mention_recyclerView.adapter = MentionAdapter(mention_data)

        // 데이터 받아오기
        val text_data = arguments?.getString("원본 글")
        val mentionList_data = arguments?.getSerializable("멘션")

        // 원본 글 세팅
        val modifyPostInput = view.findViewById<EditText>(R.id.input_modify_post)
        // 원본 멘션
        // 멘션 데이터에 넣어주기

        // 글 쓰기 구현
        modifyPostInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // *----서버/DB 필요----*
                // 사용자가 쓴 글 저장
                modified_data = s.toString()
            }
        })

        // 사람 멘션하기
        val addMentionButton = view.findViewById<TextView>(R.id.btn_add_mention)
        addMentionButton.setOnClickListener {
            // *----서버/DB 필요----*
            // 멘션한 사람들 저장
            addMention()
            mention_recyclerView.adapter?.notifyDataSetChanged()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun addMention() {
        val mention = Mention(input_mention.text.toString())
        mention_data.add(mention)
        input_mention.setText("")
    }
}