package com.sheepduck.android.timesavingbox

import android.content.Context

object TaskRepository {
    fun insertTask(context: Context, task: Task) {
        val db = TaskDatabase.getInstance(context)
        db.taskDao().insertTask(task)
    }
}