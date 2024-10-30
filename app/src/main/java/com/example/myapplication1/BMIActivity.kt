package com.example.myapplication1

import java.math.BigDecimal
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication1.databinding.ActivityBmiBinding
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    private var binding : ActivityBmiBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            setContentView(binding?.root)
            setSupportActionBar(binding?.toolbarBmiActivity)
            if(supportActionBar != null){
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.title="Calculate BMI"
            }
            binding?.toolbarBmiActivity?.setNavigationOnClickListener {
                onBackPressed()
            }
            binding?.btnCalculateUnite?.setOnClickListener {
                if (validateMetricUnit()){
                    val heightValue:Float=binding?.etMetricHeight?.text.toString().toFloat()/100
                    val weightValue:Float=binding?.etMetricWeight?.text.toString().toFloat()
                    val bmi=weightValue/(heightValue*heightValue)
                    val bmiLabel:String
                    val bmiDescription:String
                    dispalyBMIResult(bmi)

                }else{
                    Toast.makeText(this@BMIActivity,"Please enter valid values", Toast.LENGTH_SHORT).show()
                }
            }

            insets
        }
    }

    private fun dispalyBMIResult(bmi:Float){
        val bmilabel:String
        val bmiDescripation:String
        if(bmi.compareTo(15f)<=0){
            bmilabel="Very severely underweight"
            bmiDescripation="Oops! You really need to take better care of yourself! Eat more!"
        }else if(bmi.compareTo(15f)>0 && bmi.compareTo(16f)<=0){
            bmilabel="Severely Underweight"
            bmiDescripation="Ooops! You really need to take better care of yourself! Eat more!"
        }else if(bmi.compareTo(16f)>0 && bmi.compareTo(18.5f)<=0){
            bmilabel="Severely Underweight"
            bmiDescripation="Ooops! You really need to take better care of yourself! Eat more!"
        }else if(bmi.compareTo(18.5f)>0 && bmi.compareTo(25f)<=0){
            bmilabel="Normal"
            bmiDescripation="Congratulations! You are in a good shape!"
        }else if(bmi.compareTo(25f)>0 && bmi.compareTo(30f)<=0){
            bmilabel=" Overweight"
            bmiDescripation="Ooops! You really need to take better care of yourself! Workout more!"

        }else if(bmi.compareTo(30f)>0 && bmi.compareTo(35f)<=0){
            bmilabel="Obese Class | (Moderately obese)"
            bmiDescripation="Ooops! You really need to take better care of yourself! Workout more!"

        }else if(bmi.compareTo(35f)>0 && bmi.compareTo(40f)<=0){
            bmilabel="Obese Class || (Severely obese)"
            bmiDescripation="OMG! You are in a very dangerous condition! Act now!"
        }else{
            bmilabel="Obese Class ||| (Very Severely obese)"
            bmiDescripation="OMG! You are in a very dangerous condition! Act now!"
        }
        val bmiValue=BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility= View.VISIBLE
        binding?.tvBMIValue?.text=bmiValue
        binding?.tvBMIType?.text=bmilabel
        binding?.tvBMIDescription?.text=bmiDescripation

    }

    private fun validateMetricUnit(): Boolean {
        var isValid =true

        if(binding?.etMetricWeight?.text.toString().isEmpty()){
            isValid=false
        }else if(binding?.etMetricHeight?.text.toString().isEmpty()){
            isValid=false

        }
        return isValid

    }
}

