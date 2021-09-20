package com.example.illo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.ScaleAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home1.*

class Home1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home1)

        // Tab Layout & Pager 설정
        tabSetting()

        // 하단 네비게이션 설정
        bottomNaviSetting()

        // 알림 버튼
        alarm_menu.setOnClickListener {
            // Intent 생성 (홈1 -> 알림)
            val intent : Intent = Intent(this@Home1Activity, AlarmActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.no_animation)
            Log.d("intent", "홈1에서 알림 버튼 클릭")
        }

    }

    fun bottomNaviSetting() {
        bottomNavi.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    // 홈1 메인 화면 탭으로
                    home1_pager.setCurrentItem(0)   // 0번 탭으로 설정
                    Toast.makeText(this@Home1Activity, "홈1로 이동", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_my -> {
                    // My 액티비티로 전환
                    Toast.makeText(this@Home1Activity, "My로 이동", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
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
            override fun onTabReselected(tab: TabLayout.Tab?) { }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                //// 탭이 선택됐을 때

                // 알맞는 프래그먼트를 보여주기
                home1_pager.currentItem = tab!!.position
            }
        })
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