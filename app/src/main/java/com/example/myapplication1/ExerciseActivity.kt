package com.example.myapplication1

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication1.databinding.ActivityExerciseBinding
import java.util.Locale

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restprogress =0
    private var excrciserestTimer: CountDownTimer? = null
    private var excrciseresprogress =0

    private var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePosition=-1

    private var tts: TextToSpeech?=null
    private var player: MediaPlayer?=null


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
            exerciseList=Constants.defaultExercise()

            tts = TextToSpeech(this,this)
            binding?.toolbarExercise?.setNavigationOnClickListener {
                onBackPressed()
            }
            setupRestView()
            setRestProgressBar()

            insets


        }
    }
    private fun setupRestView(){

        try{
            val soundURI = Uri.parse("android.resource://com.example.myapplication1/" + R.raw.press_start)
            player=MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping=false
            player?.start()
        }catch (e: Exception){
            e.printStackTrace()
        }

        binding?.flRestView?.visibility= View.VISIBLE
        binding?.tvTitle?.visibility=View.VISIBLE
        binding?.tvExerciseName?.visibility=View.INVISIBLE
        binding?.flExerciseView?.visibility=View.INVISIBLE
        binding?.ivImage?.visibility=View.INVISIBLE
        binding?.tvUpcommingLabel?.visibility=View.VISIBLE
        binding?.tvUpCommingExerciseName?.visibility=View.VISIBLE


        if(restTimer!=null){
            restTimer?.cancel()
            restprogress=0
        }

        binding?.tvUpCommingExerciseName?.text=exerciseList!![currentExercisePosition+1].getName()

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
                currentExercisePosition++
                setupExerciseView()

            }

        }.start()
    }
    private fun setupExerciseView(){
        binding?.flRestView?.visibility= View.INVISIBLE
        binding?.tvTitle?.visibility=View.INVISIBLE
        binding?.tvExerciseName?.visibility=View.VISIBLE
        binding?.flExerciseView?.visibility=View.VISIBLE
        binding?.ivImage?.visibility=View.VISIBLE

        binding?.tvUpcommingLabel?.visibility=View.INVISIBLE
        binding?.tvUpCommingExerciseName?.visibility=View.INVISIBLE

        if(excrciserestTimer!=null){
            excrciserestTimer?.cancel()
            excrciseresprogress=0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())

        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text=exerciseList!![currentExercisePosition].getName()
        setExerciseProgressBar()
    }

    private fun setExerciseProgressBar() {
        binding?.progressbarExercise?.progress=excrciseresprogress
        excrciserestTimer=object :CountDownTimer(30000,1000){
             override fun onTick(p0: Long) {
                excrciseresprogress++
                binding?.progressbarExercise?.progress=30- excrciseresprogress
                binding?.tvTimerExercise?.text=(30-excrciseresprogress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition<exerciseList?.size!!-1){

                    setupRestView()

                    setRestProgressBar()
                }

                else {
                    Toast.makeText(this@ExerciseActivity,"Congratulation! You have completed the 7 minutes workout",Toast.LENGTH_SHORT).show()
                }

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
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()

        }
        if(player!=null){
            player!!.stop()
        }

        binding=null
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){

            val result = tts?.setLanguage(Locale.UK)

            if(result==TextToSpeech.LANG_MISSING_DATA|| result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The language specified is not supported")
            }


        }else{
            Log.e("TTS","Initialization Failed")

        }

    }
    private fun speakOut(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")

    }
}