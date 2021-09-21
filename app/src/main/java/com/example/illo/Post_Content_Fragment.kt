package com.example.illo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        // 가져오는 레이아웃 바꾸기
        val view: View = inflater.inflate(R.layout.fragment_post_content, container, false)

        img_recyclerView = view.findViewById(R.id.recyclerview_img_post)
        img_recyclerView.setHasFixedSize(true)
        img_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        img_recyclerView.adapter = PostImageAdapter(img_data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

