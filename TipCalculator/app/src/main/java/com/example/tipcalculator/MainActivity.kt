package com.example.tipcalculator

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var costOfServiceEditText: EditText
    private lateinit var tipOptionsGroup: RadioGroup
    private lateinit var roundUpSwitch: Switch
    private lateinit var tipResultTextView: TextView
    private var tipAmount = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        costOfServiceEditText = findViewById(R.id.cost_of_service)
        tipOptionsGroup = findViewById(R.id.tip_options)
        roundUpSwitch = findViewById(R.id.round_up_switch)
        tipResultTextView = findViewById(R.id.tip_result)
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val costInput = costOfServiceEditText.text.toString()

        if (TextUtils.isEmpty(costInput)) {
            costOfServiceEditText.error = "Please enter a cost"
            return
        }
        val cost = costInput.toDouble()
        val selectedId = tipOptionsGroup.checkedRadioButtonId
        val tipPercentage: Double
        tipPercentage = when (selectedId) {
            R.id.amazing_service -> 0.20
            R.id.good_service -> 0.18
            R.id.ok_service -> 0.15
            else -> 0.18
        }
        var tip = cost * tipPercentage
        if (roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }
        tipAmount += tip

        val tipResult = String.format("$%.2f", tipAmount)

        tipResultTextView.text = tipResult
    }
}
