package com.sheepduck.android.timesavingbox

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timesavingbox",
    primaryKeys = arrayOf("_id", "date"))
class Task(date:String, starttime:String, endtime:String, memo:String) {
    //@PrimaryKey(autoGenerate = true)
    var _id: Int = 1
    var date:String = date
    var starttime:String? = starttime
    var endtime:String? = endtime
    var memo:String? = memo
}