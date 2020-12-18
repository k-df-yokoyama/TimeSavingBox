package com.sheepduck.android.timesavingbox

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(startTime:String, endTime:String, memo:String) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    var startTime:String = startTime
    var endTime:String = endTime
    var memo:String = memo
}