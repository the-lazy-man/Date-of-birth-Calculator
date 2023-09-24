package com.example.dobcalc

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var selectedDate : TextView? = null
    private var ageInYear : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val  btnSelectDate : Button = findViewById(R.id.btnSelectDate)
        selectedDate = findViewById(R.id.txtSelectedDate)
        ageInYear = findViewById(R.id.txtAgeInYear)
        btnSelectDate.setOnClickListener{
            selectDate()
        }
    }
    private fun selectDate(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val maxDatePossible = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_,year,month,dayOfMonth ->
            Toast.makeText(this,"clicked on datePicker dialogue",Toast.LENGTH_SHORT).show()
            val selectedDateValue = "$dayOfMonth/${month+1}/$year"
            selectedDate?.text = selectedDateValue
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = sdf.parse(selectedDateValue)
                date?.let {
                    val timeInAYear : Long = 1000*60*60*24
                    val ageValueInYear = date.time / timeInAYear
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInYears = currentDate.time / timeInAYear
                        ageInYear?.text = ((currentDateInYears - ageValueInYear) / 365).toString()
                    }
                }

        },
        year,
        month,
        day)
        maxDatePossible.datePicker.maxDate = System.currentTimeMillis()
        maxDatePossible.show()
    }
}