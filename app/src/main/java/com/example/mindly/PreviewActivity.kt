package com.example.mindly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_preview.*
import soup.neumorphism.NeumorphButton

class PreviewActivity : AppCompatActivity() {
    private lateinit var itemButtons: List<NeumorphButton>
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        btn_mindmap_add.setOnClickListener(activityOnClickListner)
        itemButtons = listOf(
            btn_mindmap_item_1,
            btn_mindmap_item_2,
            btn_mindmap_item_3,
            btn_mindmap_item_4
        )
    }

    private val activityOnClickListner = View.OnClickListener {
        when (it.id) {
            R.id.btn_mindmap_add -> {
                setCircleLayout()
                checkVisibility()
            }
        }
    }

    private fun setCircleLayout() {
        var angle = 0f
        val constraintLayout: ConstraintLayout = constraintLayout_previewContainer
        val set = ConstraintSet()
        set.clone(constraintLayout)

        when (count) {
            0 -> angle = 230F
            1 -> angle = 330F
            2 -> angle = 140F
        }
        set.constrainCircle(
            R.id.btn_mindmap_add,
            R.id.imageView_background,
            400,
            angle
        )
        set.applyTo(constraintLayout)
        count++
    }

    private fun checkVisibility() {
        itemButtons.forEachIndexed { index, _ ->
            if (index < count) {
                itemButtons[index].visibility = View.VISIBLE
            }
        }
        if (count > 3) btn_mindmap_add.visibility = View.GONE
    }
}