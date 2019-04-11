package com.raywenderlich.tipcalculator

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

const val HUNDRED_PERCENT = 100

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

        if (amountText.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.amountTextErrorMessage), Toast.LENGTH_SHORT).show()
            return
        }

        if (tipText.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.tipTextErrorMessage), Toast.LENGTH_SHORT).show()
            return
        }

        if (peopleText.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.peopleTextDefaultValue), Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountText.text.toString().toDouble()
        val tipPercent = tipText.text.toString().toInt()
        val people = peopleText.text.toString().toInt()

        val totalAmount = amount * (HUNDRED_PERCENT + tipPercent) / HUNDRED_PERCENT
        val totalTip = tipPercent * amount
        val billPerPax = totalAmount / people
        val tipPerPax = totalTip / people

        totalBillTextView.text = String.format(getString(R.string.totalAmountText), totalAmount)
        totalTipTextView.text = String.format(getString(R.string.totalTipText), totalTip)
        billPerPaxTextView.text = String.format(getString(R.string.billPerPaxText), billPerPax)
        tipPerPaxTextView.text = String.format(getString(R.string.tipPerPaxText), tipPerPax)

        amountText.hideKeyboard()
        tipText.hideKeyboard()
        peopleText.hideKeyboard()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
