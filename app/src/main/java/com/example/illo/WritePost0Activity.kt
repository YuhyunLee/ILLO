package com.example.illo

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_write_post0.*

class WritePost0Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_post0)

        // 플로팅 버튼 클릭시 에니메이션 동작 기능
        fab_write_post.setOnClickListener {
            OpenFab()
        }

        // 게임 버튼 클릭 이벤트
        fab_game.setOnClickListener {
            Toast.makeText(this, "게임 버튼 클릭!", Toast.LENGTH_SHORT).show()
            CloseFab()
        }

        // 갤러리 버튼 클릭 이벤트
        fab_gallery.setOnClickListener {
            Toast.makeText(this, "갤러리 버튼 클릭!", Toast.LENGTH_SHORT).show()
            CloseFab()
        }

        // 배경 아무데나 클릭 시
        fade_background.setOnClickListener {
            CloseFab()
        }
    }

    /***
     *  플로팅 액션 버튼 클릭시 동작하는 애니메이션 효과 세팅
     */

    // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션 세팅
    private fun OpenFab() {
        fade_background.visibility = View.VISIBLE
        fab_game.bringToFront()

        ObjectAnimator.ofFloat(fab_game, "translationY", 0f).apply { start() }
        ObjectAnimator.ofFloat(fab_gallery, "translationY", -200f).apply { start() }
    }

    // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션 세팅
    private fun CloseFab() {
        fade_background.visibility = View.INVISIBLE
        fab_write_post.bringToFront()

        ObjectAnimator.ofFloat(fab_game, "translationY", 0f).apply { start() }
        ObjectAnimator.ofFloat(fab_gallery, "translationY", 0f).apply { start() }
    }
}

