package com.example.myapplication1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication1.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            setSupportActionBar(binding?.toolbarHistoryActivity)
            supportActionBar?.title = "HISTORY"

            binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            val dao = (application as WorkOutApp).db.historyDao()
            getAllCompletedDates(dao)

            insets
        }
    }

    private fun getAllCompletedDates(historyDao: HistoryDao) {
        Log.e("Date:", "getAllCompletedDates run")

        lifecycleScope.launch {
            historyDao.fetchAllDates().collect { allCompletedDatesList ->
                if(allCompletedDatesList.isNotEmpty()){
                   binding?.tvHistory?.visibility= View.VISIBLE
                    binding?.rvHistory?.visibility= View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility= View.INVISIBLE
                    binding?.rvHistory?.layoutManager=LinearLayoutManager(this@HistoryActivity)
                    val dates=ArrayList<String>()
                    for(date in allCompletedDatesList){
                        dates.add(date.date)
                    }
                    val historyAdapter=HistoryAdapater(dates)

                    binding?.rvHistory?.adapter=historyAdapter

                }else{
                    binding?.tvHistory?.visibility= View.INVISIBLE
                    binding?.rvHistory?.visibility= View.INVISIBLE
                    binding?.tvNoDataAvailable?.visibility= View.VISIBLE
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
