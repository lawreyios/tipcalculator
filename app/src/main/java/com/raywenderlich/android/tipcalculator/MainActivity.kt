package com.raywenderlich.android.tipcalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

const val HUNDRED_PERCENT = 100.00

class MainActivity : AppCompatActivity(), View.OnClickListener, TextWatcher {

    // Top Section
    private lateinit var expensePerPersonTextView: TextView
    private lateinit var billEditText: EditText

    // Tip Section
    private lateinit var addTipButton: ImageButton
    private lateinit var tipTextView: TextView
    private lateinit var subtractTipButton: ImageButton

    // Number of People Section
    private lateinit var addPeopleButton: ImageButton
    private lateinit var numberOfPeopleTextView: TextView
    private lateinit var subtractPeopleButton: ImageButton

    private var numberOfPeople = 4 // set default number of people
    private var tipPercent = 20 // set default tip percent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        expensePerPersonTextView = findViewById(R.id.expensePerPersonTextView)
        billEditText = findViewById(R.id.billEditText)
        billEditText.addTextChangedListener(this)

        addTipButton = findViewById(R.id.addTipButton)
        addTipButton.setOnClickListener(this)
        tipTextView = findViewById(R.id.tipTextView)
        subtractTipButton = findViewById(R.id.subtractTipButton)
        subtractTipButton.setOnClickListener(this)

        addPeopleButton = findViewById(R.id.addPeopleButton)
        addPeopleButton.setOnClickListener(this)
        numberOfPeopleTextView = findViewById(R.id.numberOfPeopleTextView)
        subtractPeopleButton = findViewById(R.id.subtractPeopleButton)
        subtractPeopleButton.setOnClickListener(this)
    }

    private fun calculateExpense() {

        val totalBill = billEditText.text.toString().toDouble()

        val totalExpense = ((HUNDRED_PERCENT + tipPercent) / HUNDRED_PERCENT) * totalBill
        val individualExpense = totalExpense / numberOfPeople

        expensePerPersonTextView.text = String.format("$%.2f", individualExpense)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.addTipButton -> incrementTip()
            R.id.subtractTipButton -> decrementTip()
            R.id.addPeopleButton -> incrementPeople()
            R.id.subtractPeopleButton -> decrementPeople()
        }
    }

    private fun incrementTip() {
        tipPercent += 5
        tipTextView.text = String.format("%d%%", tipPercent)
        calculateExpense()
    }

    private fun decrementTip() {
        if (tipPercent != 0) {
            tipPercent -= 5
            tipTextView.text = String.format("%d%%", tipPercent)
            calculateExpense()
        }
    }

    private fun incrementPeople() {
        numberOfPeople += 1
        numberOfPeopleTextView.text = numberOfPeople.toString()
        calculateExpense()
    }

    private fun decrementPeople() {
        if (numberOfPeople != 0) {
            numberOfPeople -= 1
            numberOfPeopleTextView.text = numberOfPeople.toString()
            calculateExpense()
        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (!billEditText.text.isEmpty()) {
            calculateExpense()
        }
    }

    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
}
