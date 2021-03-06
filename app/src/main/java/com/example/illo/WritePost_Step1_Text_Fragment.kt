package com.example.illo

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_write_post_step1_text.*
import java.io.Serializable

class WritePost_Step1_Text_Fragment : Fragment() {
    lateinit var mention_recyclerView: RecyclerView    // 멘션 리사이클러뷰
    val mention_data = arrayListOf<Mention>()  // 멘션 데이터
    lateinit var written_data : String      // 작성한 글 데이터

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_write_post_step1_text, container, false)

        // 멘션 리사이클러뷰 설정
        mention_recyclerView = view.findViewById(R.id.recyclerview_mention_write_post)
        mention_recyclerView.setHasFixedSize(true)
        mention_recyclerView.layoutManager = GridLayoutManager(activity, 7)
        mention_recyclerView.adapter = MentionAdapter(mention_data)

        // 글 쓰기 구현
        val writePostInput = view.findViewById<EditText>(R.id.input_write_post)
        writePostInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // *----서버/DB 필요----*
                // 사용자가 쓴 글 저장
                written_data = s.toString()
            }
        })

        // 사람 멘션하기
        val addMentionButton = view.findViewById<TextView>(R.id.btn_add_mention)
        addMentionButton.setOnClickListener {
            // *----서버/DB 필요----*
            // 멘션한 사람들 저장
            addMention()
            mention_recyclerView.adapter?.notifyDataSetChanged()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun addMention() {
        val mention = Mention(input_mention.text.toString())
        mention_data.add(mention)
        input_mention.setText("")
    }

}

data class Mention(val name : String) : Serializable

class MentionAdapter(private val dataSet: List<Mention>) :
    RecyclerView.Adapter<MentionAdapter.MentionViewHolder>() {

    class MentionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name : TextView

        init {
            name = view.findViewById(R.id.tag_text)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MentionViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_tag, viewGroup, false)

        return MentionViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MentionViewHolder, position: Int) {
        viewHolder.name.text = dataSet[position].name

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

