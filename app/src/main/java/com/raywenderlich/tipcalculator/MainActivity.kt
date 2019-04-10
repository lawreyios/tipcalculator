package com.raywenderlich.tipcalculator

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Calculator
    private lateinit var amountText: EditText
    private lateinit var tipText: EditText
    private lateinit var peopleText: EditText

    // Bill Receipt
    private lateinit var totalBillTextView: TextView
    private lateinit var totalTipTextView: TextView
    private lateinit var billPerPaxTextView: TextView
    private lateinit var tipPerPaxTextView: TextView

    // Button
    private lateinit var equalButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        amountText = findViewById(R.id.amountText)
        tipText = findViewById(R.id.tipText)
        peopleText = findViewById(R.id.peopleText)

        totalBillTextView = findViewById(R.id.totalBillTextView)
        totalTipTextView = findViewById(R.id.totalTipTextView)
        billPerPaxTextView = findViewById(R.id.billPerPaxTextView)
        tipPerPaxTextView = findViewById(R.id.tipPerPaxTextView)

        equalButton = findViewById(R.id.equalButton)
        equalButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val amount = amountText.text.toString().toDouble();
        val tip = tipText.text.toString().toInt();
        val people = peopleText.text.toString().toInt();

        val totalAmount = amount * (100 + tip)/100
        val totalTip = tip * amount
        val billPerPax = totalAmount / people
        val tipPerPax = totalTip / people

        totalBillTextView.text = "Total Bill: $" + String.format("%.2f", totalAmount);
        totalTipTextView.text = "Total Tip: $" + String.format("%.2f", totalTip);
        billPerPaxTextView.text = "Bill per pax: $" + String.format("%.2f", billPerPax);
        tipPerPaxTextView.text = "Tip per pax: $" + String.format("%.2f", tipPerPax);

        amountText.hideKeyboard()
        tipText.hideKeyboard()
        peopleText.hideKeyboard()
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
