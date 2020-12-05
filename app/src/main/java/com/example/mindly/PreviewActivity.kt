package com.example.mindly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.mindly.mindmap.MindMapActivity
import kotlinx.android.synthetic.main.activity_preview.*
import soup.neumorphism.NeumorphButton

class PreviewActivity : AppCompatActivity() {
    private lateinit var itemButtons: List<NeumorphButton>
    private var itemCount = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        btn_mindmap_add.visibility = View.GONE
        button_start.setOnClickListener(activityOnClickListener)
        itemButtons = listOf(
            btn_mindmap_item_1,
            btn_mindmap_item_2,
            btn_mindmap_item_3,
            btn_mindmap_item_4
        )
        setCircleLayout()
        checkVisibility()
    }

    private val activityOnClickListener = View.OnClickListener {
        when (it.id) {
            R.id.button_start -> {
                val intent = Intent(this, MindMapActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setCircleLayout() {
        var angle = 0f
        val constraintLayout: ConstraintLayout = constraintLayout_previewContainer
        val set = ConstraintSet()
        set.clone(constraintLayout)

        when (itemCount) {
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
    }

    private fun checkVisibility() {
        itemButtons.forEachIndexed { index, _ ->
            if (index < itemCount) {
                itemButtons[index].visibility = View.VISIBLE
            }
        }
    }
}