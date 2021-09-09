package com.example.illo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home1.*

class Home1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home1)

        // Tab Layout & Pager 설정
        tabSetting()

        // 알림 버튼
        alarm_menu.setOnClickListener {
            // Intent 생성 (홈1 -> 알림)
            val intent : Intent = Intent(this@Home1Activity, AlarmActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.no_animation)
            Log.d("intent","홈1에서 알림 버튼 클릭")
        }

        // fab 버튼
        fab_home1.setOnClickListener {
            // fab 애니메이션 실행
            scaleUp(fab_home1, 500)

            // Intent 생성 (홈1 -> 모임 만들기)
            val intent : Intent = Intent(this@Home1Activity, CreateMoimActivity0::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.no_animation)
        }
    }

    fun tabSetting() {
        // tab layout 설정
        home1_tab_layout.addTab(home1_tab_layout.newTab().setText("홈"))
        home1_tab_layout.addTab(home1_tab_layout.newTab().setText("다가오는"))

        // pager adpapter 설정
        val pagerAdapter = Home1_PagerAdapter(supportFragmentManager, 2)
        home1_pager.adapter = pagerAdapter

        // pager를 넘겼을 때 tab도 넘어가도록 해주기
        home1_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(home1_tab_layout))

        // tab layout과 pager 연동
        home1_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                //// 탭이 선택됐을 때

                // 알맞는 프래그먼트를 보여주기
                home1_pager.currentItem = tab!!.position
            }
        })
    }

    fun scaleUp(view: View, time: Int) {
        val anim = ScaleAnimation(1.0f,2.0f,1.0f,2.0f,200f,200f)    // 애니메이션 로드
        anim.duration = time.toLong()   // 애니메이션 지속시간
        view.startAnimation(anim)   // 애니메이션 실행
    }
}

class Home1_PagerAdapter(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        // 아이템(프래그먼트) 하나를 반환
        when (position) {
            0 -> {
                return Home1_0_Fragment()
            }
            1 -> {
                return Home1_1_Fragment()
            }
            else -> return Home1_0_Fragment()
        }
    }

    override fun getCount(): Int {
        // pager 개수 반환
        return tabCount
    }
}