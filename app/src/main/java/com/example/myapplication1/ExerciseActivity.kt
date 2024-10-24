package com.example.myapplication1

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication1.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restprogress =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

             setSupportActionBar(binding?.toolbarExercise)
            if(supportActionBar != null){
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            binding?.toolbarExercise?.setNavigationOnClickListener {
                onBackPressed()
            }
            setupRestView()
            setRestProgressBar()

            insets


        }
    }
    private fun setupRestView(){
        if(restTimer!=null){
            restTimer?.cancel()
            restprogress=0
        }
    }

    private fun setRestProgressBar() {
        binding?.progressbar?.progress=restprogress
        restTimer=object :CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                ++restprogress
                binding?.progressbar?.progress=10- restprogress
                binding?.tvTimer?.text=(10-restprogress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"Here now we will start the exercise",Toast.LENGTH_SHORT).show()

            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restprogress=0
        }
        binding=null
    }
}