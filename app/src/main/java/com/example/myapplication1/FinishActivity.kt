package com.example.myapplication1

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication1.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class FinishActivity : AppCompatActivity() {
    private var binding: ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            setSupportActionBar(binding?.toolbarExercisefinish)
            if (supportActionBar != null) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            binding?.toolbarExercisefinish?.setNavigationOnClickListener {
                onBackPressed()
            }
            binding?.btnfinish?.setOnClickListener {
                finish()
            }
            val dao=(application as WorkOutApp).db.historyDao()
            addDataToDatabase(dao)


            insets
        }
    }
    private fun addDataToDatabase(historyDao: HistoryDao){

        val c=Calendar.getInstance()
        val dateTime=c.time
        Log.e("Date:",""+dateTime)
        val sdf= SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date=sdf.format(dateTime)
        Log.e("Formatted Date:",""+date)


        lifecycleScope.launch{
            historyDao.insert(HistoryEntity(date))
            Log.e("Date:","Added...")
        }
    }
}