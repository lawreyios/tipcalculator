package com.raywenderlich.android.tipcalculator

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
    private lateinit var numberOfPeopleText: EditText

    // Bill Receipt
    private lateinit var totalBillTextView: TextView
    private lateinit var totalTipTextView: TextView
    private lateinit var billPerPersonTextView: TextView
    private lateinit var tipPerPersonTextView: TextView

    // Button
    private lateinit var calculateButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        amountText = findViewById(R.id.amountText)
        tipText = findViewById(R.id.tipText)
        numberOfPeopleText = findViewById(R.id.numberOfPeopleText)

        totalBillTextView = findViewById(R.id.totalBillTextView)
        totalTipTextView = findViewById(R.id.totalTipTextView)
        billPerPersonTextView = findViewById(R.id.billPerPersonTextView)
        tipPerPersonTextView = findViewById(R.id.tipPerPersonTextView)

        calculateButton = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
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

        if (numberOfPeopleText.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.numberOfPeopleTextDefaultValue), Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountText.text.toString().toDouble()
        val tipPercent = tipText.text.toString().toInt()
        val numberOfPeople = numberOfPeopleText.text.toString().toInt()

        val totalAmount = amount * (HUNDRED_PERCENT + tipPercent) / HUNDRED_PERCENT
        val totalTip = tipPercent * amount
        val billPerPerson = totalAmount / numberOfPeople
        val tipPerPerson = totalTip / numberOfPeople

        totalBillTextView.text = String.format(getString(R.string.totalAmountText), totalAmount)
        totalTipTextView.text = String.format(getString(R.string.totalTipText), totalTip)
        billPerPersonTextView.text = String.format(getString(R.string.billPerPersonText), billPerPerson)
        tipPerPersonTextView.text = String.format(getString(R.string.tipPerPersonText), tipPerPerson)

        amountText.hideKeyboard()
        tipText.hideKeyboard()
        numberOfPeopleText.hideKeyboard()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
