package com.example.myapplication1

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
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
    private var excrciserestTimer: CountDownTimer? = null
    private var excrciseresprogress =0


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
            //setupExerciseView()


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
                setupExerciseView()

            }

        }.start()
    }
    private fun setupExerciseView(){
        binding?.flProgressBar?.visibility= View.INVISIBLE
        binding?.tvTitle?.text="Exercise Name"
        binding?.flExerciseView?.visibility=View.VISIBLE
        if(excrciserestTimer!=null){
            excrciserestTimer?.cancel()
            excrciseresprogress=0
        }
        setExerciseProgressBar()
    }

    private fun setExerciseProgressBar() {
        binding?.progressbarExercise?.progress=excrciseresprogress
        excrciserestTimer=object :CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                ++excrciseresprogress
                binding?.progressbarExercise?.progress=30- excrciseresprogress
                binding?.tvTimerExercise?.text=(30-excrciseresprogress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"30 seconds are over , lets go to the rest view",Toast.LENGTH_SHORT).show()

            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restprogress=0
        }
        if(excrciserestTimer!=null){
            excrciserestTimer?.cancel()
            excrciseresprogress=0
        }

        binding=null
    }
}