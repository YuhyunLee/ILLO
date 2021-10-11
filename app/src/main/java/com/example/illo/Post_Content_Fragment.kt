package com.example.illo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_post_content.*

class Post_Content_Fragment : Fragment() {

    lateinit var img_recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_post_content, container, false)

        // *--서버/DB에서 데이터 가져오기--*
        // 작성자 이미지
        val writerImg = view.findViewById<ImageView>(R.id.writer_img)
        // 작성자 이름
        val writerName = view.findViewById<TextView>(R.id.writer_name)
        // 작성 일시
        val writerDate = view.findViewById<TextView>(R.id.writer_date)
        // 글 내용
        val postContent = view.findViewById<TextView>(R.id.post_content)

        // 이미지 리사이클러뷰
        img_recyclerView = view.findViewById(R.id.recyclerview_img_post)
        img_recyclerView.setHasFixedSize(true)
        img_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        img_recyclerView.adapter = PostImageAdapter(img_data)

        // 멘션한 사람들
        val mentionsPost = view.findViewById<TextView>(R.id.mentions_post)

        // 좋아요
        val postLikes = view.findViewById<ImageView>(R.id.post_likes)
        postLikes.setOnClickListener {
            // 좋아요 수에 +1
            var num : Int = post_likes_num.text.toString().toInt()
            num += 1
            post_likes_num.text = num.toString()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

