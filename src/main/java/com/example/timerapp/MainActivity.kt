package com.example.timerapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val START_IN_MILLI:Long = 25 * 60 * 1000
    var reminingTime :Long= START_IN_MILLI
    var time : CountDownTimer?=null
    var istimestart = false

    lateinit var Titre :TextView
    lateinit var Time:TextView
    lateinit var START :Button
    lateinit var RESET :TextView
    lateinit var pb :ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Titre = findViewById(R.id.titre)
        Time = findViewById(R.id.time)
        START = findViewById(R.id.start)
        RESET = findViewById(R.id.reset)
        pb= findViewById(R.id.progressBar)

        START.setOnClickListener {
            if(istimestart==false){
                startTime()
                Titre.text  =resources.getText(R.string.keep_going)
            }


        }
        RESET.setOnClickListener {
            resetTime()
        }

    }

    private fun startTime() {
         time = object : CountDownTimer(START_IN_MILLI, 1 * 1000) {
            override fun onTick(timeleft: Long) {
                reminingTime = timeleft
                updateTimerText()
                pb.progress= reminingTime.toDouble().div(START_IN_MILLI.toDouble()).times(100).toInt()

            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Finish....", Toast.LENGTH_LONG).show()
                istimestart=false
            }

        }.start()

        istimestart= true
    }
    private  fun resetTime(){
        time?.cancel()
        reminingTime=START_IN_MILLI
        updateTimerText()
        Titre.text = resources.getText(R.string.take_promodoro)
        istimestart=false
        pb.progress=100
    }

    private  fun  updateTimerText(){
        val minute = reminingTime.div(1000).div(60)
        val second = reminingTime.div(1000)% 60
        val formatedTime = String.format("%02d:%02d ",minute,second)
        Time.text= formatedTime
    }
}


