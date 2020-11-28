package com.sheepduck.android.timesavingbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    //データベースヘルパーオブジェクト。
    private val _helper = DatabaseHelper(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        //ヘルパーオブジェクトの開放。
        _helper.close()
        super.onDestroy()
    }

    fun onSaveButtonClick(view: View) {
        val etStartTime = findViewById<EditText>(R.id.et_start_time)
        val startTime = etStartTime.text.toString()
        val etEndTime = findViewById<EditText>(R.id.et_end_time)
        val endTime = etEndTime.text.toString()
        val etMemo = findViewById<EditText>(R.id.et_memo)
        val memo = etMemo.text.toString()

        val db = _helper.writableDatabase

        //val sqlInsert = "INSERT INTO timesavingbox (_id, date, starttime, endtime, memo) VALUES (?, ?, ?, ?, ?, ?)"
        val sqlInsert = "INSERT OR REPLACE INTO timesavingbox (_id, date, starttime, endtime, memo) VALUES (1, ?, ?, ?, ?)"
        var stmt = db.compileStatement(sqlInsert)
        //TODO("LocalDate format yyyy-MM-dd 2019-07-04 is expected") //https://codechacha.com/ja/kotlin-examples-current-date-and-time/

        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = current.format(formatter)
        stmt.bindString(1, formatted)
        stmt.bindString(2, startTime)
        stmt.bindString(3, endTime)
        stmt.bindString(4, memo)
        stmt.executeInsert()

        //delete input value
        //etMemo.setText("")

        val tvSavedMsg = findViewById<TextView>(R.id.tv_saved_msg).apply {
            text = getResources().getString(R.string.tv_saved_msg)
        }
    }
}
