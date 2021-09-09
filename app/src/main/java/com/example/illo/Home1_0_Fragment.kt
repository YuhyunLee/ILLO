package com.example.illo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home1_0.*

// 모임 리스트
val moimList = arrayListOf<Moim>()

class Home1_0_Fragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home1_0, container, false)

        createMoimData()

        recyclerView = view.findViewById(R.id.recyclerview_moim_card)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = MoimAdapter(moimList)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 모임이 없을 때
        if (moimList.isNullOrEmpty())
            no_moim.visibility = View.VISIBLE

    }

    fun createMoimData() {
        moimList.add(Moim(R.drawable.illust_thumb, "이리오개", "유기견 봉사활동"))
        moimList.add(Moim(R.drawable.illust_3_fingers, "옵치할사람", "티어가 어디든 환영"))
        moimList.add(Moim(R.drawable.playing_basketball, "신한우승", "다음 시즌 신한 이겨라"))
    }
}


// 모임 아이템 클래스
data class Moim(val img_moim: Int, val text_moim_name: String, val text_moim_cap: String)

// 모임카드 어댑터
class MoimAdapter(private val dataSet: List<Moim>) :
    RecyclerView.Adapter<MoimAdapter.MoimViewHolder>() {

    class MoimViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img_moim: ImageView
        val text_moim_cap: TextView
        val text_moim_name: TextView

        init {
            img_moim = view.findViewById(R.id.img_moim)
            text_moim_cap = view.findViewById(R.id.text_moim_cap)
            text_moim_name = view.findViewById(R.id.text_moim_name)
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoimViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_moim_card, viewGroup, false)

        return MoimViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MoimViewHolder, position: Int) {

        viewHolder.img_moim.setBackgroundResource(R.drawable.card_my_moim)
        viewHolder.img_moim.clipToOutline = true
        viewHolder.img_moim.setImageResource(dataSet[position].img_moim)

        viewHolder.text_moim_cap.text = dataSet[position].text_moim_cap
        viewHolder.text_moim_name.text = dataSet[position].text_moim_name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
