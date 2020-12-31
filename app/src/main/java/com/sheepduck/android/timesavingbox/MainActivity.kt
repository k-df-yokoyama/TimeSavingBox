package com.sheepduck.android.timesavingbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.with as with1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun onSaveButtonClick(view: View) {
        val etStartTime = findViewById<EditText>(R.id.et_start_time)
        val etEndTime = findViewById<EditText>(R.id.et_end_time)
        val etMemo = findViewById<EditText>(R.id.et_memo)

        //TODO("LocalDate format yyyy-MM-dd 2019-07-04 is expected") //https://codechacha.com/ja/kotlin-examples-current-date-and-time/

        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = current.format(formatter)

        var task:Task = Task(formatted, etStartTime.text.toString(), etEndTime.text.toString(), etMemo.text.toString())

        TaskRepository.insertTask(applicationContext, task)

        //delete input value
        //etMemo.setText("")

        val tvSavedMsg = findViewById<TextView>(R.id.tv_saved_msg).apply {
            text = getResources().getString(R.string.tv_saved_msg)
        }
    }

    fun onNewButtonClick(view: View) {
        val etStartTime = findViewById<EditText>(R.id.et_start_time)
        val etEndTime = findViewById<EditText>(R.id.et_end_time)
        val etMemo = findViewById<EditText>(R.id.et_memo)

        //delete input value
        etStartTime.setText("")
        etEndTime.setText("")
        etMemo.setText("")
        val tvSavedMsg = findViewById<TextView>(R.id.tv_saved_msg).apply {
            text = ""
        }
    }
}
