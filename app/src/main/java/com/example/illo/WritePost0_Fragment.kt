package com.example.illo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

val call_data = arrayListOf<Calouts>()
val img_data = arrayListOf<PostImage>()

class Post0_Fragment : Fragment() {
    lateinit var call_recyclerView: RecyclerView
    lateinit var img_recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_write_post0, container, false)

        createData()

        // 리사이클러뷰 정리
        call_recyclerView = view.findViewById(R.id.recyclerview_calouts_write_post)
        call_recyclerView.setHasFixedSize(true)
        call_recyclerView.layoutManager = GridLayoutManager(activity, 7)
        call_recyclerView.adapter = CaloutsAdapter(call_data)

        img_recyclerView = view.findViewById(R.id.recyclerview_img_write_post)
        img_recyclerView.setHasFixedSize(true)
        img_recyclerView.layoutManager = LinearLayoutManager(activity)
        img_recyclerView.adapter = PostImageAdapter(img_data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun createData() {
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))

        img_data.add(PostImage(R.drawable.cat))
        img_data.add(PostImage(R.drawable.wallet))
    }
}

data class Calouts(val name : String)
data class PostImage(val img : Int)

class CaloutsAdapter(private val dataSet: List<Calouts>) :
    RecyclerView.Adapter<CaloutsAdapter.CaloutsViewHolder>() {

    class CaloutsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name : TextView

        init {
            name = view.findViewById(R.id.txt_name)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CaloutsViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_tag, viewGroup, false)

        return CaloutsViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CaloutsViewHolder, position: Int) {
        viewHolder.name.text = dataSet[position].name

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}


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
