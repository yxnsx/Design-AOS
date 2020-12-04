package com.example.mindly.mindmap

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.mindly.R
import kotlinx.android.synthetic.main.activity_mindmap.*
import soup.neumorphism.NeumorphButton

class MindMapActivity : AppCompatActivity() {
    private lateinit var itemButtons: List<NeumorphButton>
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mindmap)

        setStatusBarTransparent()

        addClickListener()

        itemButtons = listOf(btn_mindmap_item_1, btn_mindmap_item_2, btn_mindmap_item_3, btn_mindmap_item_4)
    }

    private fun addClickListener(){
        btn_mindmap_add.setOnClickListener {
            setCircleLayout()
            checkVisibility()
        }
    }

    private fun setCircleLayout(){
        var angle = 0f

        val constraintLayout: ConstraintLayout = cl_mindmap
        val set = ConstraintSet()
        set.clone(constraintLayout)

        when(count){
            0 -> angle = 220F
            1 -> angle = 310F
            2 -> angle = 150F
        }

        set.constrainCircle(
            R.id.btn_mindmap_add,
            R.id.imageView2,
            450,
            angle
        )

        set.applyTo(constraintLayout)

        count++
    }

    private fun checkVisibility(){
        itemButtons.forEachIndexed { index, _ ->
            if(index < count){
                itemButtons[index].visibility = View.VISIBLE
            }
        }

        if(count > 3) btn_mindmap_add.visibility = View.GONE
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_mindmap.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) setDarkStatusBar()
    }

    // 상태바 어둡게
    private fun setDarkStatusBar() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }
}