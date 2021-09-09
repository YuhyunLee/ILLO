package com.example.illo


import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.shrikanthravi.collapsiblecalendarview.data.Day
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import kotlinx.android.synthetic.main.dialog_input_schedule_date.*
import java.util.*

class Input_Schedule_Date_Dialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 전체화면 스타일 설정
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true


        val collapsibleCalendar: CollapsibleCalendar? = view?.findViewById(R.id.calender_input_schedule_date)
        val today = GregorianCalendar()

        collapsibleCalendar?.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_input_schedule_date, container, false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
