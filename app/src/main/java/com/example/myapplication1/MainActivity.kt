package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            //val flStartButton: FrameLayout = findViewById(R.id.flstart)
            binding?.flstart?.setOnClickListener {
                val intent = Intent(this, ExerciseActivity::class.java)
                startActivity(intent)
            }

            binding?.flBMI?.setOnClickListener {
                val intent=Intent(this,BMIActivity::class.java)
                startActivity(intent)
            }
            binding?.flHistory?.setOnClickListener {
                val intent=Intent(this,HistoryActivity::class.java)
                startActivity(intent)
            }
            fun onDestroy() {
                super.onDestroy()
                binding = null
            }

            insets
        }

    }
}