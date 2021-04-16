package com.project.gradecalculator

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideSystemUI()
        calcButton.setOnClickListener{
            val grade1 = grade1.editText?.text.toString().toDouble()
            val grade2 = grade2.editText?.text.toString().toDouble()
            val absenceAccount = absenceAccount.editText?.text.toString().toInt()
            val media: Double = (grade1 + grade2) / 2
            when {
                media >= 6 && absenceAccount < 10 -> {
                    calcFeedback.text = String.format(
                        "%s\n%s\n%s",
                        "Student was approved",
                        "Final grade: $media",
                        "Absence account: $absenceAccount"
                    )
                    calcFeedback.setTextColor(Color.GREEN)
                }
                media < 6 && absenceAccount < 10 -> {
                    calcFeedback.text = String.format(
                        "%s\n%s\n%s",
                        "Student was not approved because of low grade",
                        "Final grade: $media",
                        "Absense account: $absenceAccount"
                    )
                    calcFeedback.setTextColor(Color.RED)
                }
                media >= 6 && absenceAccount >= 10 -> {
                    calcFeedback.text = String.format(
                        "%s\n%s\n%s",
                        "Student was not approved because of the number of absense",
                        "Final grade: $media",
                        "Absense account: $absenceAccount"
                    )
                    calcFeedback.setTextColor(Color.RED)
                }
                else -> {
                    calcFeedback.text = String.format(
                        "%s\n%s\n%s",
                        "Student was not approved because of the number of absense and the low grade",
                        "Final grade: $media",
                        "Absense account: $absenceAccount"
                    )
                    calcFeedback.setTextColor(Color.RED)
                }

            }
        }

    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
    }
}