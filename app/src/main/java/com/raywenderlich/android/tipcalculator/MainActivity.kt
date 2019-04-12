package com.raywenderlich.android.tipcalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

const val HUNDRED_PERCENT = 100.00
const val TIP_INCREMENT_PERCENT = 20
const val PEOPLE_INCREMENT_VALUE = 1

//TODO #7: Implements Button Listener
//TODO #15: Implements TextWatcher
class MainActivity : AppCompatActivity() {

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
        //TODO #6: Connect the Views
        //TODO #13: Bind Buttons to Listener
        //TODO #16: Bind EditText to TextWatcher
    }

    private fun calculateExpense() {
        //TODO #14: Implement and call calculateExpense as required
    }

    //TODO #8: Override onClick and implement a switch case to handle multiple buttons

    private fun incrementTip() {
        //TODO #9: Implement Tip Increment
    }

    private fun decrementTip() {
        //TODO #10: Implement Tip Decrement
    }

    private fun incrementPeople() {
        //TODO #11: Implement People Increment
    }

    private fun decrementPeople() {
        //TODO #12: Implement People Decrement
    }

    //TODO #17: Implement onTextChanged, beforeTextChanged & afterTextChanged
}
