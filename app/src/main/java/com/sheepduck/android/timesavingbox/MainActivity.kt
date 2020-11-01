package com.sheepduck.android.timesavingbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import java.nio.file.attribute.UserDefinedFileAttributeView
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
        val etMemo = findViewById<EditText>(R.id.editText)
        val memo = etMemo.text.toString()

        val db = _helper.writableDatabase

        //val sqlInsert = "INSERT INTO timesavingbox (_id, date, starttime, endtime, memo) VALUES (?, ?, ?, ?, ?, ?)"
        val sqlInsert = "INSERT INTO timesavingbox (date, starttime, endtime, memo) VALUES (?, ?, ?, ?)"
        var stmt = db.compileStatement(sqlInsert)
        //TODO("LocalDate format yyyy-MM-dd 2019-07-04 is expected") //https://codechacha.com/ja/kotlin-examples-current-date-and-time/

        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = current.format(formatter)
        stmt.bindString(1, formatted)
        stmt.bindString(2, "starttime")
        stmt.bindString(3, "endtime")
        stmt.bindString(4, memo)
        stmt.executeInsert()

        //delete input value
        etMemo.setText("")
    }
}
