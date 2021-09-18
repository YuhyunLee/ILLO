package com.example.illo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.illo.databinding.ActivityAlarmBinding
import com.example.illo.databinding.ItemAlarmBinding
import kotlinx.android.synthetic.main.activity_alarm.*

class AlarmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlarmBinding

    // 알림 데이터 리스트
    val alarmList = arrayListOf<AlarmItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 툴바 설정
        val alarmToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_alarm)
        setSupportActionBar(alarmToolbar)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 보여주기 비활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_to_left);    // 뒤로 가기 버튼 커스텀
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로 가기 버튼 자동 생성

        // 뒤로 가기 버튼 설정
        toolbar_alarm.setNavigationOnClickListener {
            finish()
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)
        }

        // 전체 삭제하기 버튼
        btn_delete_all.setOnClickListener {
            // 알림 리스트에 있는 데이터 모두 삭제
            alarmList.clear()
            // 아답타에게 알린다
            binding.recyclerviewAlarm.getAdapter()?.notifyDataSetChanged()
        }

        // 알림 리사이클러뷰
        binding.recyclerviewAlarm.layoutManager = LinearLayoutManager(this@AlarmActivity)
        binding.recyclerviewAlarm.adapter = AlarmAdapter(createData())
        // setup swipe to remove item
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewAlarm)

    }



    fun createData() : ArrayList<AlarmItem> {
        alarmList.add(AlarmItem("김민지님이 게시글에서 나를 언급했습니다", "슬램덩크"))
        alarmList.add(AlarmItem("3일 후에 한강 플로깅 일정이 있습니다", "나눔 서포터즈"))
        alarmList.add(AlarmItem("지혜님이 내 글에 댓글을 달았습니다", "열정 봉사단"))

        return alarmList
    }

    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                Toast.makeText(this@AlarmActivity, "On Move", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                // 삭제되는 아이템의 포지션을 가져온다
                val position = viewHolder.adapterPosition
                // 데이터의 해당 포지션을 삭제한다
                alarmList.removeAt(position)
                // 아답타에게 알린다
                binding.recyclerviewAlarm.getAdapter()?.notifyItemRemoved(position)
            }
        }

}

// 알림 아이템 클래스
data class AlarmItem(val text_alarm: String, val text_from_moim: String)

// 알림 어댑터
class AlarmAdapter(private val dataSet: List<AlarmItem>) :
    RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    class AlarmViewHolder(val binding: ItemAlarmBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlarmAdapter.AlarmViewHolder
    {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_alarm, viewGroup, false)

        return AlarmViewHolder(ItemAlarmBinding.bind(view))
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.binding.textAlarm.text = dataSet[position].text_alarm
        holder.binding.textFromMoim.text = dataSet[position].text_from_moim
    }


    override fun getItemCount() = dataSet.size

}