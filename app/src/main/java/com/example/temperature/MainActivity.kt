package com.example.temperature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.temperature.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
binding.calculateButton.setOnClickListener{ calculateTemp() }
    }
    private fun calculateTemp(){
val stringInTextField = binding.temperatureEditText.text.toString()
        var Temp = stringInTextField.toDoubleOrNull()
        if(Temp == null){
            binding.result.text = ""
            return
        }
        val selectedId = binding.temperatureOptions.checkedRadioButtonId
        var final  = when(selectedId){
            R.id.option_celcius -> Temp
            R.id.option_fahrenheit -> (Temp * 1.8) + 32
            else -> Temp + 273.15
        }

        val roundUp = binding.roundoffTemperature.isChecked
        if (roundUp){
            final = kotlin.math.ceil(final)
        }
        NumberFormat.getNumberInstance()
        val formattedTemp = NumberFormat.getNumberInstance().format(final)
        binding.result.text =getString(R.string.converted_temperature, formattedTemp)

    }
}
