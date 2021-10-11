package com.example.illo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_write_post.*

class PostActivity : AppCompatActivity() {
    val hashTagData = arrayListOf<PostHashTag>()
    lateinit var hashtag_recyclerview : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        // 툴바 설정
        toolbarSetting()

        // 해시태그 리사이클러뷰 생성
        hashtag_recyclerview = findViewById(R.id.recyclerview_hashtag)
        hashtag_recyclerview.setHasFixedSize(true)
        hashtag_recyclerview.layoutManager = LinearLayoutManager(
            this@PostActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        hashtag_recyclerview.adapter = PostHashTagAdapter(hashTagData)

        // 서버에서 데이터 가져오기
        // 글, 멘션, 작성자, 작성일시, 작성자 프로필
        // ((((임의로 데이터 생성))))
        hashTagData.add(PostHashTag("#대꿀잼"))
        hashTagData.add(PostHashTag("#가보자고"))

        // 수정하기 버튼 구현
        btn_post_modify.setOnClickListener{
            Post_Modify_Dialog().show(supportFragmentManager, "Post2_Modify_Dialog")


        }
    }

    // 툴바 세팅
    fun toolbarSetting() {
        // 툴바로 세팅
        setSupportActionBar(toolbar_post)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_to_left);    // 뒤로 가기 버튼 커스텀
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로 가기 버튼 자동 생성

        // 뒤로가기 버튼 설정
        toolbar_post.setNavigationOnClickListener {
            finish()    // 액티비티 끝내기
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)   // 애니메이션
        }
    }

    public fun deleteAlertDialog() {
        Post_Delete_Dialog().show(supportFragmentManager, "Post2_Delete_Dialog")
    }

}

// 해시태그 데이터
data class PostHashTag(val tagText : String)

class PostHashTagAdapter(private val dataSet: List<PostHashTag>) :
    RecyclerView.Adapter<PostHashTagAdapter.PostHashTagViewHolder>() {

    class PostHashTagViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tag_text : TextView

        init {
            tag_text = view.findViewById(R.id.tag_text)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostHashTagViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_tag, viewGroup, false)

        return PostHashTagViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: PostHashTagViewHolder, position: Int) {
        viewHolder.tag_text.text = dataSet[position].tagText
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}