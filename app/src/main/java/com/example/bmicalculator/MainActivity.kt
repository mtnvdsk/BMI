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
        val weigthText=findViewById<EditText>(R.id.weigthnum)
        val heigthText=findViewById<EditText>(R.id.heigthnum)
        val calcButton=findViewById<Button>(R.id.btncal)
        calcButton.setOnClickListener{
            val weigth=weigthText.text.toString()
            val heigth=heigthText.text.toString()
            if (validate(weigth,heigth)) {
                val bmi = weigth.toFloat() / ((heigth.toFloat() / 100) * (heigth.toFloat() / 100))
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }

    private fun validate(weigth:String?,heigth:String?):Boolean{
        return when{
            weigth.isNullOrEmpty() -> {
                Toast.makeText(this,"This weigth is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            heigth.isNullOrEmpty() -> {
                Toast.makeText(this,"This heigth is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayResult(bmi:Float){
        val info=findViewById<TextView>(R.id.info)
        val index=findViewById<TextView>(R.id.tv1)
        val result=findViewById<TextView>(R.id.tvresult)
        index.text=bmi.toString()
        info.text="(Normal range is 18.5-24.9)"

        var resultText=""
        var color=0

        when{
            bmi<18.50 ->{
                resultText="Underweigth"
                color = R.color.under_weigth
            }
            bmi in 18.50..24.99->{
                resultText="Healthy"
                color=R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText="overweigth"
                color=R.color.over_weigth
            }
            bmi >29.99->{
                resultText="Obeese"
                color=R.color.obese
            }
        }

        result.setTextColor(ContextCompat.getColor(this,color))
        result.text=resultText
    }
}