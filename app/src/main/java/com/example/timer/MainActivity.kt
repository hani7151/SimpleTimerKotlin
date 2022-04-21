package com.example.timer

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import com.example.timer.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var chronometer: Chronometer

    var running: Boolean = false
    var pause: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        chronometer = c_meter

        btn_start.setOnClickListener {
            if (!running) {
                chronometer.base = SystemClock.elapsedRealtime() - pause
                chronometer.start()
                running = true
                btn_start.visibility = View.INVISIBLE
                btn_reset.visibility = View.VISIBLE
                btn_reset.setText(R.string.stop)
                btn_reset.setBackgroundColor(getColor(R.color.red))
            } else {
                pause = SystemClock.elapsedRealtime() - chronometer.base
                chronometer.stop()
                running = false
                btn_start.setText(R.string.resume)
                btn_reset.setText(R.string.reset)
                btn_reset.setBackgroundColor(getColor(R.color.purple_500))

            }
        }

        btn_reset.setOnClickListener {
            if (!running) {
                chronometer.base = SystemClock.elapsedRealtime()
                pause = 0
                btn_start.setText(R.string.start)
                btn_reset.visibility = View.INVISIBLE
            } else {
                chronometer.stop()
                pause = SystemClock.elapsedRealtime() - chronometer.base
                btn_start.visibility = View.VISIBLE
                btn_start.setText(R.string.resume)
                btn_reset.setText(R.string.reset)
                running = false
            }
        }
    }
}