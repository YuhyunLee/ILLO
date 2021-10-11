package com.example.illo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class Post_Comment_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_post_comment, container, false)

        // 댓글 작성자 이미지
        val commenterImg = view.findViewById<ImageView>(R.id.commenter_img)
        // 댓글 작성자 이름
        val commenterName = view.findViewById<TextView>(R.id.commenter_name)
        // 댓글 작성 일시
        val commenterTime = view.findViewById<TextView>(R.id.commenter_time)
        // 댓글 내용
        val commentText = view.findViewById<TextView>(R.id.comment_text)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}