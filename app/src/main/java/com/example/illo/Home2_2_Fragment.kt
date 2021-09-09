package com.example.illo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shrikanthravi.collapsiblecalendarview.data.Day
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import com.shrikanthravi.collapsiblecalendarview.widget.UICalendar
import kotlinx.android.synthetic.main.fragment_home2_2.*
import java.util.*

class Home2_2_Fragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    val data = arrayListOf<MoimSchedule>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 가져오는 레이아웃 바꾸기
        val view: View = inflater.inflate(R.layout.fragment_home2_2, container, false)

        createMoimScheduleData()

        recyclerView = view.findViewById(R.id.recyclerview_moim_schedule)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MoimScheduleAdapter(data)

        val collapsibleCalendar: CollapsibleCalendar = view.findViewById(R.id.calender_moim_schedule)
        val today = GregorianCalendar()
        // 달력 특정 날짜에 이벤트 추가

        collapsibleCalendar.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
            override fun onDaySelect() {
                val day: Day = collapsibleCalendar.getSelectedDay()
                Log.i(
                    javaClass.name, "Selected Day: "
                            + day.year + "/" + (day.month + 1) + "/" + day.day
                )
            }

            override fun onItemClick(view: View) {}
            override fun onDataUpdate() {}
            override fun onMonthChange() {}
            override fun onWeekChange(i: Int) {}

        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 게시글이 없을 때
        if (data.isNullOrEmpty())
            no_moim_schedule.visibility = View.VISIBLE
    }

    fun createMoimScheduleData() {
        data.add(MoimSchedule("토요경기","서울시 동작구 상도동 365","16:00"))
        data.add(MoimSchedule("번개","망원유수지","11:00"))
        data.add(MoimSchedule("방탈출 갈 사람","건대","12:00"))
    }

}

data class MoimSchedule(val schedule_title : String, val place : String, val time : String)

class MoimScheduleAdapter(private val dataSet: List<MoimSchedule>) :
    RecyclerView.Adapter<MoimScheduleAdapter.MoimScheduleViewHolder>() {

    class MoimScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title_schedule : TextView
        val place : TextView
        val time : TextView

        init {
            title_schedule = view.findViewById(R.id.title_schedule)
            place = view.findViewById(R.id.place)
            time = view.findViewById(R.id.time)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoimScheduleViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_moim_schedule, viewGroup, false)

        return MoimScheduleViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: MoimScheduleViewHolder, position: Int) {

        viewHolder.title_schedule.text = dataSet[position].schedule_title
        viewHolder.place.text = dataSet[position].place
        viewHolder.time.text = dataSet[position].time

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}



