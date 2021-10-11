package com.example.illo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_post_modify.*
import kotlinx.android.synthetic.main.activity_write_post.*

class PostModifyActivity : AppCompatActivity() {
    var text_data : String? = ""
    var hashTag_data : String? = ""
    var mentionList_data = ArrayList<Mention>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_modify)

        // 데이터 받아오기
        text_data = intent.getStringExtra("원본 글")
        hashTag_data = intent.getStringExtra("해시태그")
        mentionList_data = intent.getSerializableExtra("멘션") as ArrayList<Mention>

        // 툴바 설정
        toolbarSetting()

        // 디폴트는 step1
        ShowStep1()

        // step 이동 버튼
        btn_modify_step1.setOnClickListener {
            ShowStep1()
            btn_modify_step1.setImageResource(R.drawable.icon_first_step_enable)
            btn_modify_step2.setImageResource(R.drawable.icon_second_step_disable)
        }
        btn_modify_step2.setOnClickListener {
            ShowStep2()
            btn_modify_step1.setImageResource(R.drawable.icon_first_step_disable)
            btn_modify_step2.setImageResource(R.drawable.icon_second_step_enable)
        }

        // 글 쓰기 완료 버튼! (누르면 게시글이 등록됨)
        btn_modify_post_ok.setOnClickListener {
            // *----서버/DB 필요----*
            // 포스트로 등록

            // 액티비티 종료
            finish()
            Toast.makeText(this, "글쓰기 완료!", Toast.LENGTH_SHORT).show()
        }
    }

    // 툴바 세팅
    fun toolbarSetting() {
        // 툴바로 세팅
        setSupportActionBar(toolbar_post_modify)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_to_left);    // 뒤로 가기 버튼 커스텀
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로 가기 버튼 자동 생성

        // 뒤로가기 버튼 설정
        toolbar_post_modify.setNavigationOnClickListener {
            finish()    // 액티비티 끝내기
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_left)   // 애니메이션
        }

    }

    fun ShowStep1()
    {
        val fragmentManager : FragmentManager = supportFragmentManager  // 프래그먼트 매니저 생성
        val fragmentTransaction = fragmentManager.beginTransaction()    // 트랜잭션 시작

        // 데이터 전달
        val postmodifyStep1Fragment = PostModify_Step1_Fragment()
        val bundle = Bundle()
        bundle.putString("원본 글", text_data)
        bundle.putSerializable("멘션", mentionList_data)
        postmodifyStep1Fragment.arguments = bundle

        fragmentTransaction.replace(R.id.fragment_container_modify_post, postmodifyStep1Fragment)    // ID뷰를 프래그먼트로 대체
        fragmentTransaction.commit()    // 트랜잭션 끝내기
    }

    fun ShowStep2()
    {
        val fragmentManager : FragmentManager = supportFragmentManager  // 프래그먼트 매니저 생성
        val fragmentTransaction = fragmentManager.beginTransaction()    // 트랜잭션 시작

        // 데이터 전달
        val postmodifyStep2Fragment = PostModify_Step2_Fragment()
        val bundle = Bundle()
        bundle.putString("원본 글", hashTag_data)
        postmodifyStep2Fragment.arguments = bundle

        fragmentTransaction.replace(R.id.fragment_container_modify_post, postmodifyStep2Fragment)    // ID뷰를 프래그먼트로 대체
        fragmentTransaction.commit()    // 트랜잭션 끝내기
    }
}