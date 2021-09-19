package com.example.illo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home2_0.*

class Home2_0_Fragment : Fragment() {

    lateinit var recyclerView: RecyclerView // 공지 리사이클러뷰
    val data = arrayListOf<Post>()  // 게시글 데이터

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home2_0, container, false)

        // 데이터 생성
        creatPostData()

        // 리사이클러뷰 설정
        recyclerView = view.findViewById(R.id.recyclerview_notice)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = NoticeAdapter(data)

        // 모임 수정 버튼 클릭 이벤트
        val editMoimInfoButton = view.findViewById<ImageView>(R.id.btn_edit_moim_info)
        editMoimInfoButton.setOnClickListener {
            // 홈2 --> 모임 수정1 액티비티
            // Fragment --> Activity 이동
            activity?.let {
                val intent: Intent = Intent(activity, EditMoimInfoActivity0::class.java)
                startActivity(intent)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 공지가 없을 때
        if(data.isNullOrEmpty())
            no_notice.visibility = View.VISIBLE

    }

    // *----서버/DB 필요----*
    fun creatPostData()
    {
        data.add(Post(true, "7월 16일 경기 사진 공유합니다 다들 고생하셨어요~", R.drawable.blue_court))
        data.add(Post(true, "코로나 19관련 경기 공지", R.drawable.masks))
        data.add(Post(true, "하반기 활동에 대한 공지", R.drawable.playing_basketball))
    }
}

data class Post(val isNotice : Boolean, val title : String, val thumbnail : Int)

class NoticeAdapter(private val dataSet: List<Post>) :
    RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {

    class NoticeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title : TextView
        val thumbnail : ImageView

        init {
            title = view.findViewById(R.id.txt_content_title)
            thumbnail = view.findViewById(R.id.img_content_thumbnail)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NoticeViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_post, viewGroup, false)

        return NoticeViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: NoticeViewHolder, position: Int) {

        // 공지가 맞다면
        if (dataSet[position].isNotice)
        {
            viewHolder.title.text = dataSet[position].title

            viewHolder.thumbnail.setBackgroundResource(R.drawable.card_content)
            viewHolder.thumbnail.clipToOutline = true
            viewHolder.thumbnail.setImageResource(dataSet[position].thumbnail)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
