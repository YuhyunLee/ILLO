package com.example.illo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import android.widget.AdapterView
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home1_0.*


class Home1_0_Fragment : Fragment() {
    // 모임 리스트
    val moimList = arrayListOf<Moim>()
    // 내가 가입한 모임 리스트
    val myMoimList = arrayListOf<Moim>()
    // 지금 보여지고 있는 모임 리스트
    val shownList = arrayListOf<Moim>()

    // 모임 리사이클러뷰, 어댑터
    lateinit var moimListrecyclerView : RecyclerView
    val moimAdapter = MoimAdapter(shownList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home1_0, container, false)

        // 모임 데이터 생성
        createMoimData()
        createMyMoimData()
        shownList.addAll(myMoimList)

        // 모임 리사이클러뷰 설정 (내가 가입한 모임)
        moimListrecyclerView = view.findViewById(R.id.recyclerview_moim_card)
        moimListrecyclerView.setHasFixedSize(true)
        moimListrecyclerView.layoutManager = GridLayoutManager(activity, 2)
        moimListrecyclerView.adapter = moimAdapter

        // 리사이클러뷰 아이템 클릭 (모임 클릭 시!!)
        moimAdapter.setItemClickListener(object: MoimAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // 클릭 시 이벤트 작성
                
                // 가입한 모임이라면 해당 모임 페이지(홈2)로 이동
                if(moimList[position].joined) {
                    // Fragment --> Activity 이동
                    activity?.let {
                        val intent: Intent = Intent(activity, Home2Activity::class.java)
                        startActivity(intent)
                    }
                }
                // 아니라면 가입 다이얼로그 띄우기
                else {
                    // Fragment --> DialogFragment 띄우기
                    val bundle = Bundle()
                    val dialog: Home1_0_Join_Moim_Dialog = Home1_0_Join_Moim_Dialog().getInstance()
                    dialog.arguments = bundle
                    activity?.supportFragmentManager?.let { fragmentManager ->
                        dialog.show(
                            fragmentManager, "Join Moim Dialog"
                        )
                    }
                }
            }
        })

        // 서치뷰 가져오기
        val searchView : SearchView = view.findViewById(R.id.moim_search_view)
        searchView.isSubmitButtonEnabled = true // 검색 버튼 활성화
        // 모임 검색창에 입력 시
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색 버튼을 눌렀을 때
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색창 텍스트에 변화가 있을 때
                // search 메소드를 호출한다
                val text : String = newText.toString()
                search(text)

                return false
            }
        })

        // fab 버튼
        val fab_btn = view.findViewById<FloatingActionButton>(R.id.fab_home1_0)
        fab_btn.setOnClickListener {
            // fab 애니메이션 실행
            scaleUp(fab_btn, 500)

            // Intent 생성 (홈1 -> 모임 만들기)
            // Fragment --> Activity 이동
            activity?.let {
                val intent : Intent = Intent(activity, CreateMoimActivity0::class.java)
                startActivity(intent)
                // 애니메이션 나중에 다시 수정!!
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 가입한 모임이 없을 때
        if (moimList.isNullOrEmpty())
            no_moim.visibility = View.VISIBLE

    }

    // *----서버/DB 필요----*
    fun createMoimData() {
        moimList.add(Moim(true, R.drawable.illust_thumb, "이리오개", "유기견 봉사활동"))
        moimList.add(Moim(true, R.drawable.illust_3_fingers, "옵치할사람", "티어가 어디든 환영"))
        moimList.add(Moim(true, R.drawable.playing_basketball, "신한우승", "다음 시즌 신한 이겨라"))
        moimList.add(Moim(false, R.drawable.illust_thumb, "이리오개", "유기견 봉사활동"))
        moimList.add(Moim(false, R.drawable.illust_thumb, "이리오개", "유기견 봉사활동"))
        moimList.add(Moim(false, R.drawable.illust_thumb, "이리오개", "유기견 봉사활동"))
    }

    fun createMyMoimData() {
        for (i in moimList) {
            // 모임 리스트를 순회하여 가입한 모임이면 내 모임 리스트에 넣는다
            if (i.joined)
                myMoimList.add(i)
        }
    }

    // 검색을 수행하는 함수
    fun search(charText: String) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        shownList.clear()

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length == 0) {
            // 내 모임 리스트를 보여준다
            shownList.addAll(myMoimList)
        } else {
            // 리스트의 모든 데이터를 검색한다.
            for (i in 0 until moimList.size) {
                // 모임 리스트의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (moimList.get(i).moim_name.toLowerCase().contains(charText)) {
                    // 검색된 데이터를 리스트에 추가한다.
                    shownList.add(moimList.get(i))
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        moimListrecyclerView.adapter?.notifyDataSetChanged()
    }

    // 애니메이션 함수
    fun scaleUp(view: View, time: Int) {
        val anim = ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, 200f, 200f)    // 애니메이션 로드
        anim.duration = time.toLong()   // 애니메이션 지속시간
        view.startAnimation(anim)   // 애니메이션 실행
    }
}


// 모임 아이템 클래스
data class Moim(
    val joined: Boolean,
    val img_moim: Int,
    val moim_name: String,
    val moim_cap: String
)

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

        // 모임 데이터 설정
        viewHolder.img_moim.setBackgroundResource(R.drawable.card_my_moim)
        viewHolder.img_moim.clipToOutline = true
        viewHolder.img_moim.setImageResource(dataSet[position].img_moim)

        viewHolder.text_moim_cap.text = dataSet[position].moim_cap
        viewHolder.text_moim_name.text = dataSet[position].moim_name

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
