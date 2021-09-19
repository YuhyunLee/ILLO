package com.example.illo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home1.*
import kotlinx.android.synthetic.main.activity_home2.*

class Home2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        // tab layout 설정
        home2_tab_layout.addTab(home2_tab_layout.newTab().setText("홈"))
        home2_tab_layout.addTab(home2_tab_layout.newTab().setText("게시판"))
        home2_tab_layout.addTab(home2_tab_layout.newTab().setText("스케줄"))
        home2_tab_layout.addTab(home2_tab_layout.newTab().setText("비용"))
        home2_tab_layout.addTab(home2_tab_layout.newTab().setText("채팅"))

        // pager adpapter 설정
        val pagerAdapter = Home2_PagerAdapter(supportFragmentManager, 5)
        home2_pager.adapter = pagerAdapter

        // pager를 넘겼을 때 tab도 넘어가도록 해주기
        home2_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(home2_tab_layout))

        // tab layout과 pager 연동
        home2_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) { }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                //// 탭이 선택됐을 때

                // 알맞는 프래그먼트를 보여주기
                home2_pager.currentItem = tab!!.position
            }
        })
    }
}

class Home2_PagerAdapter(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        // 아이템(프래그먼트) 하나를 반환
        when (position) {
            0 -> {
                return Home2_0_Fragment()
            }
            1 -> {
                return Home2_1_Fragment()
            }
            2 -> {
                return Home2_2_Fragment()
            }
            3 -> {
                return Home2_3_Fragment()
            }
            4 -> {
                return Home2_4_Fragment()
            }
            else -> return Home2_0_Fragment()
        }
    }

    override fun getCount(): Int {
        // pager 개수 반환
        return tabCount
    }
}