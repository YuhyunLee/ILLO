package com.example.illo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home1_1.*

class Home1_1_Fragment :Fragment() {

    // 리사이클러 뷰
    lateinit var schedule_recyclerView : RecyclerView
    lateinit var fee_recyclerView : RecyclerView
    lateinit var nbbang_recyclerView : RecyclerView

    // 어댑터
    lateinit var scheduleCardAdapter: ScheduleCardAdapter
    lateinit var feeCardAdapter: FeeCardAdapter
    lateinit var nbbangCardAdapter: NbbangCardAdapter

    val scheduleDataList = arrayListOf<ScheduleCard>()  // 다가오는 스케줄 데이터
    val feeDataList = arrayListOf<FeeCard>()            // 회비 데이터
    val nbbangDataList = arrayListOf<NbbangCard>()      // N분의1 데이터

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?) : View? {

        val view : View = inflater.inflate(R.layout.fragment_home1_1, container, false)

        // 데이터 생성
        createScheduleData()
        createFeeData()
        createNbbangData()

        // 어댑터 설정
        scheduleCardAdapter = ScheduleCardAdapter(scheduleDataList)
        feeCardAdapter = FeeCardAdapter(feeDataList)
        nbbangCardAdapter = NbbangCardAdapter(nbbangDataList)

        // 스케줄 리사이클러뷰
        schedule_recyclerView = view.findViewById(R.id.recyclerview_schedule)
        schedule_recyclerView.setHasFixedSize(true)
        schedule_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        schedule_recyclerView.adapter = scheduleCardAdapter

        // 회비 리사이클러뷰
        fee_recyclerView = view.findViewById(R.id.recyclerview_fee)
        fee_recyclerView.setHasFixedSize(true)
        fee_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        fee_recyclerView.adapter = feeCardAdapter

        // N분의 일
        nbbang_recyclerView = view.findViewById(R.id.recyclerview_nbbang)
        nbbang_recyclerView.setHasFixedSize(true)
        nbbang_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        nbbang_recyclerView.adapter = nbbangCardAdapter

        // 리사이클러뷰 아이템 클릭 리스너
        RecyclerViewItemSetOnClick()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("life_cycle", "F onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        // 스케줄 데이터가 없을 때
        if(scheduleDataList.isNullOrEmpty())
            no_schedule.visibility = View.VISIBLE

        // 회비 데이터가 없을 때
        if(feeDataList.isNullOrEmpty())
            no_fee.visibility = View.VISIBLE

        // N분의 1 데이터가 없을 때
        if(nbbangDataList.isNullOrEmpty())
            no_nbbang.visibility = View.VISIBLE

    }

    //// 데이터 생성하는 함수들
    // *----서버/DB 필요----*
    fun createScheduleData()
    {
        scheduleDataList.add(ScheduleCard("토요경기","7월 31일","서울시 동작구 상도로 356","17:00","슬램덩크"))
        scheduleDataList.add(ScheduleCard("플로깅","8월 3일","뚝섬유원지","16:00","열정 봉사단"))
        scheduleDataList.add(ScheduleCard("봉사활동","9월 2일","망원유수지","11:00","슬램덩크"))

    }

    fun createFeeData()
    {
        feeDataList.add(FeeCard(true,"8월 31일","정기회비","15000","슬램덩크"))
        feeDataList.add(FeeCard(false,"9월 2일","유현이 생일선물","5000","열정 봉사단"))
        feeDataList.add(FeeCard(true,"9월 11일","대관","10000","슬램덩크"))

    }

    fun createNbbangData()
    {
//        nbbangData.add(NbbangCard(true,"8월 22일","대윤","4000","게임팟"))

    }

    // 리사이클러뷰 아이템 클릭 시 실행되는 함수
    // (코드가 너무 길어져 따로 함수로 뺌)
    fun RecyclerViewItemSetOnClick() {
        // 스케줄 어답터
        scheduleCardAdapter.setItemClickListener(object: ScheduleCardAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                //// 클릭 시 이벤트 작성

                // 스케줄 게시글로 이동
                // Fragment --> Activity 이동
                activity?.let {
                    val intent: Intent = Intent(activity, ScheduleActivity::class.java)
                    startActivity(intent)
                }
            }
        })

        // 회비 어댑터
        feeCardAdapter.setItemClickListener(object: FeeCardAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                //// 클릭 시 이벤트 작성

                // 해당 스케줄 게시글로 이동
                // Fragment --> Activity 이동
                activity?.let {
                    // !!!!!!!!!!!!!!!!임의로 해놓은것!! 수정할 것!!
                    val intent: Intent = Intent(activity, ScheduleActivity::class.java)
                    startActivity(intent)
                }
            }
        })

        // N분의1 어댑터
        nbbangCardAdapter.setItemClickListener(object: NbbangCardAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                //// 클릭 시 이벤트 작성

                // 해당 스케줄 게시글로 이동
                // Fragment --> Activity 이동
                activity?.let {
                    // !!!!!!!!!!!!!!!!임의로 해놓은것!! 수정할 것!!
                    val intent: Intent = Intent(activity, ScheduleActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }
}

class ScheduleCard(val schedule : String, val date : String, val place : String, val time : String, val moim_name : String)
class FeeCard(val isCheck : Boolean, val due: String, val fee: String, val sum: String, val moim_name: String)
class NbbangCard(val isCheck : Boolean, val due: String, val fee: String, val sum: String, val moim_name: String)

class ScheduleCardAdapter(private val dataSet: List<ScheduleCard>) :
    RecyclerView.Adapter<ScheduleCardAdapter.ScheduleCardViewHolder>() {

    class ScheduleCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val schedule: TextView
        val date : TextView
        val place : TextView
        val time : TextView
        val moim_name : TextView

        init {
            schedule = view.findViewById(R.id.schedule)
            date = view.findViewById(R.id.date)
            place = view.findViewById(R.id.place)
            time = view.findViewById(R.id.time)
            moim_name = view.findViewById(R.id.moim_name)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ScheduleCardViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_schedule, viewGroup, false)

        return ScheduleCardViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ScheduleCardViewHolder, position: Int) {
        //// 데이터 설정
        viewHolder.schedule.text = dataSet[position].schedule
        viewHolder.date.text = dataSet[position].date
        viewHolder.place.text = dataSet[position].place
        viewHolder.time.text = dataSet[position].time
        viewHolder.moim_name.text = dataSet[position].moim_name

        //// 클릭 이벤트 리스너
        // 리스트 내 항목 클릭 시 onClick() 호출
        viewHolder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }
    override fun getItemCount() = dataSet.size

    // 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
}

class FeeCardAdapter(private val dataSet: List<FeeCard>) :
    RecyclerView.Adapter<FeeCardAdapter.FeeCardViewHolder>() {

    class FeeCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val isCheck : ImageView
        val due : TextView
        val fee : TextView
        val sum : TextView
        val moim_name : TextView

        init {
            isCheck = view.findViewById(R.id.isCheck)
            due = view.findViewById(R.id.due)
            fee = view.findViewById(R.id.fee)
            sum = view.findViewById(R.id.sum)
            moim_name = view.findViewById(R.id.moim_name)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FeeCardViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_fee, viewGroup, false)

        return FeeCardViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FeeCardViewHolder, position: Int) {
        //// 데이터 설정
        // 송금했다면 체크 이미지
        if(dataSet[position].isCheck)
            viewHolder.isCheck.setImageResource(R.drawable.icon_check)
        // 아니라면 빈 이미지 넣기
        else
            viewHolder.isCheck.setImageResource(R.drawable.icon_uncheck)

        viewHolder.due.text = dataSet[position].due
        viewHolder.fee.text = dataSet[position].fee
        viewHolder.sum.text = dataSet[position].sum
        viewHolder.moim_name.text = dataSet[position].moim_name

        //// 클릭 이벤트 리스너
        // 리스트 내 항목 클릭 시 onClick() 호출
        viewHolder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount() = dataSet.size

    // 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
}

class NbbangCardAdapter(private val dataSet: List<NbbangCard>) :
    RecyclerView.Adapter<NbbangCardAdapter.NbbangCardViewHolder>() {

    class NbbangCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val isCheck : ImageView
        val due : TextView
        val fee : TextView
        val sum : TextView
        val moim_name : TextView

        init {
            isCheck = view.findViewById(R.id.isCheck)
            due = view.findViewById(R.id.due)
            fee = view.findViewById(R.id.fee)
            sum = view.findViewById(R.id.sum)
            moim_name = view.findViewById(R.id.moim_name)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NbbangCardViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_fee, viewGroup, false)

        return NbbangCardViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NbbangCardViewHolder, position: Int) {
        //// 데이터 설정
        // 송금했다면 체크 이미지
        if(dataSet[position].isCheck)
            viewHolder.isCheck.setImageResource(R.drawable.icon_check)
        // 아니라면 빈 이미지 넣기
        else
            viewHolder.isCheck.setImageResource(R.drawable.icon_uncheck)

        viewHolder.due.text = dataSet[position].due
        viewHolder.fee.text = dataSet[position].fee
        viewHolder.sum.text = dataSet[position].sum
        viewHolder.moim_name.text = dataSet[position].moim_name

        //// 클릭 이벤트 리스너
        // 리스트 내 항목 클릭 시 onClick() 호출
        viewHolder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount() = dataSet.size

    // 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
}