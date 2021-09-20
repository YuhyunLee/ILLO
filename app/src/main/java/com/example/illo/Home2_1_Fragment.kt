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
import kotlinx.android.synthetic.main.fragment_home2_1.*

//// 홈2(동아리 홈) 게시판 탭

class Home2_1_Fragment : Fragment() {
    val postList = arrayListOf<Post>()  // 게시글 데이터

    lateinit var post_recyclerView: RecyclerView    // 게시글 리사이클러뷰
    val post_Adapter = PostAdapter(postList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home2_1, container, false)

        // 게시글 데이터 생성
        creatPostData()

        // 게시글 리사이클러뷰 설정
        post_recyclerView = view.findViewById(R.id.recyclerview_post)
        post_recyclerView.setHasFixedSize(true)
        post_recyclerView.layoutManager = GridLayoutManager(activity, 2)    // 그리드 레이아웃으로
        post_recyclerView.adapter = post_Adapter


        // 리사이클러뷰 아이템 클릭 (게시글 클릭 시!!)
        post_Adapter.setItemClickListener(object: PostAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // 클릭 시 이벤트 작성

                // 홈2 --> 포스트 액티비티로 이동
                // Fragment --> Activity 이동
                activity?.let {
                    val intent: Intent = Intent(activity, PostActivity::class.java)
                    startActivity(intent)
                }
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 게시글이 없을 때
        if (postList.isNullOrEmpty())
            no_post.visibility = View.VISIBLE
    }

    fun creatPostData()
    {
        postList.add(Post(true, "7월 16일 경기 사진 공유합니다 다들 고생하셨어요~", R.drawable.blue_court))
        postList.add(Post(true, "코로나 19관련 경기 공지", R.drawable.masks))
        postList.add(Post(true, "하반기 활동에 대한 공지", R.drawable.playing_basketball))
        postList.add(Post(false,"지난주 월요일", R.drawable.raining))
        postList.add(Post(false,"에이셉 내 통장잔고 반쪼가리", R.drawable.wallet))
        postList.add(Post(false,"울집 고앵이 자랑", R.drawable.cat))
    }
}


class PostAdapter(private val dataSet: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title : TextView
        val thumbnail : ImageView

        init {
            title = view.findViewById(R.id.txt_content_title)
            thumbnail = view.findViewById(R.id.img_content_thumbnail)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_post, viewGroup, false)

        return PostViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: PostViewHolder, position: Int) {
        // 데이터 설정
        viewHolder.title.text = dataSet[position].title

        viewHolder.thumbnail.setBackgroundResource(R.drawable.card_content)
        viewHolder.thumbnail.clipToOutline = true
        viewHolder.thumbnail.setImageResource(dataSet[position].thumbnail)

        // 클릭 이벤트 리스너
        // (1) 리스트 내 항목 클릭 시 onClick() 호출
        viewHolder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
}

