package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightEditText = findViewById<EditText>(R.id.edtWeight)
        val heightEditText = findViewById<EditText>(R.id.edtHeight)

        val calculateButton = findViewById<Button>(R.id.btnCalculate)

        calculateButton.setOnClickListener {
            val weightValue = weightEditText.text.toString()
            val heightValue = heightEditText.text.toString()
            if(validateInput(weightValue, heightValue)){
                val bmi = (weightValue.toFloat()) / ((heightValue.toFloat() / 100) * (heightValue.toFloat() / 100))
                //get result with 2 decimal places
                val bmi2Decimal = String.format("%.2f", bmi).toFloat()

                displayResults(bmi2Decimal)
            }
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayResults(bmi:Float){
        val indexTextView = findViewById<TextView>(R.id.tvIndex)
        val resultTextView = findViewById<TextView>(R.id.tvResult)
        val infoTextView = findViewById<TextView>(R.id.tvInfo)

        indexTextView.text = bmi.toString()

        var info = ""
        var result = ""
        var color = 0

        when{
            bmi < 18.5 ->{
                result = "You are Underweight!"
                info = "Underweight range is no more than 18.4"
                color = R.color.underweight
            }
           bmi in 18.5..24.9 ->{
                result = "You are Healthy!"
                info = "Normal range is 18.5 - 24.9"
                color = R.color.normal
            }bmi in 25.0..29.9 ->{
                result = "You are Overweight!"
                info = "Overweight range is 25.0 - 29.9"
                color = R.color.overweight
            } bmi>=30.0 ->{
                result = "You are Obese!"
                info = "Obese range is bigger than 30.0"
                color = R.color.obese
            }
            else -> {
                result = "Wrong index!"
                info = "Please try again!"
                color = R.color.obese
            }
        }

        resultTextView.text = result
        resultTextView.setTextColor(ContextCompat.getColor(this,color))
        infoTextView.text = info
    }

}