package com.example.illo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

val call_data = arrayListOf<Calouts>()  // 콜아웃 데이터
lateinit var written_data : String      // 작성한 글 데이터

class WritePost0_Text_Fragment : Fragment() {
    lateinit var call_recyclerView: RecyclerView    // 콜아웃 리사이클러뷰

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_write_post_step1_text, container, false)

        // 콜아웃 데이터 생성
        createCaloutsData()

        // 콜아웃 리사이클러뷰 설정
        call_recyclerView = view.findViewById(R.id.recyclerview_calouts_write_post)
        call_recyclerView.setHasFixedSize(true)
        call_recyclerView.layoutManager = GridLayoutManager(activity, 7)
        call_recyclerView.adapter = CaloutsAdapter(call_data)

        // 글 쓰기 구현
        val writePostInput = view.findViewById<EditText>(R.id.input_write_post)
        writePostInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // 사용자가 쓴 글 저장
                written_data = s.toString()
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun createCaloutsData() {
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))
        call_data.add(Calouts("최예나"))

    }
}

data class Calouts(val name : String)

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

