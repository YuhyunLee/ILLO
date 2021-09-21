package com.example.illo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_write_post_step1.*

val img_data = arrayListOf<PostImage>()     // 이미지 데이터

class WritePost_Step1_Fragment : Fragment() {
    lateinit var img_recyclerView: RecyclerView // 이미지 리사이클러뷰

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_write_post_step1, container, false)

        // 데이터 생성
        createImageData()

        // 이미지 리사이클러뷰 생성
        img_recyclerView = view.findViewById(R.id.recyclerview_img_write_post)
        img_recyclerView.setHasFixedSize(true)
        img_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        img_recyclerView.adapter = PostImageAdapter(img_data)

        // Fab 버튼 설정
        FabSetting()

        return view

    }

    fun FabSetting() {
        // 플로팅 버튼 클릭시 에니메이션 동작 기능
        val addMediaFab = view?.findViewById<FloatingActionButton>(R.id.fab_add_media)
        addMediaFab?.setOnClickListener {
            OpenFab()
        }

        // 게임 버튼 클릭 이벤트
        val gameFab = view?.findViewById<FloatingActionButton>(R.id.fab_game)
        gameFab?.setOnClickListener {
            Toast.makeText(activity, "게임 버튼 클릭!", Toast.LENGTH_SHORT).show()
            CloseFab()
        }

        // 갤러리 버튼 클릭 이벤트
        val galleryFab = view?.findViewById<FloatingActionButton>(R.id.fab_gallery)
        galleryFab?.setOnClickListener {
            Toast.makeText(activity, "갤러리 버튼 클릭!", Toast.LENGTH_SHORT).show()
            CloseFab()
        }

        // 배경 아무데나 클릭 시
        val backgroundFade = view?.findViewById<ImageView>(R.id.fade_background)
        backgroundFade?.setOnClickListener {
            CloseFab()
        }
    }

    /***
     *  플로팅 액션 버튼 클릭시 동작하는 애니메이션 효과 세팅
     */

    // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션 세팅
    private fun OpenFab() {
        fade_background.visibility = View.VISIBLE
        fab_game.bringToFront()

        ObjectAnimator.ofFloat(fab_game, "translationY", 0f).apply { start() }
        ObjectAnimator.ofFloat(fab_gallery, "translationY", -200f).apply { start() }
    }

    // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션 세팅
    private fun CloseFab() {
        fade_background.visibility = View.INVISIBLE
        fab_add_media.bringToFront()

        ObjectAnimator.ofFloat(fab_game, "translationY", 0f).apply { start() }
        ObjectAnimator.ofFloat(fab_gallery, "translationY", 0f).apply { start() }
    }

    // 이미지 데이터 생성하는 함수
    fun createImageData() {
        img_data.add(PostImage(R.drawable.cat))
        img_data.add(PostImage(R.drawable.wallet))
    }
}


// 데이터 클래스
data class PostImage(val img : Int)

class PostImageAdapter(private val dataSet: List<PostImage>) :
    RecyclerView.Adapter<PostImageAdapter.PostImageViewHolder>() {

    class PostImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image : ImageView

        init {
            image = view.findViewById(R.id.img_post)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostImageViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_image_post, viewGroup, false)

        return PostImageViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: PostImageViewHolder, position: Int) {
        viewHolder.image.setBackgroundResource(R.drawable.card_attached_picture)
        viewHolder.image.clipToOutline = true
        viewHolder.image.setImageResource(dataSet[position].img)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
