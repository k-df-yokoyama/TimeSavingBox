package com.sheepduck.android.timesavingbox

import android.content.Context
import android.os.Build
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.assertj.core.api.Assertions.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.test.InstrumentationRegistry
import org.robolectric.annotation.Config
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class TaskRepositoryTest {
    lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun insertTask_finishSuccessfully() {
        var allTask = TaskRepository.loadAllTask(context)
        assertThat(allTask).isEmpty()

        val etStartTime = "2020/12/31 00:00:00"
        val etEndTime = "2020/12/31 01:00:00"
        val etMemo = "memo"
        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = current.format(formatter)

        var task:Task = Task(formatted, etStartTime, etEndTime, etMemo)

        TaskRepository.insertTask(context, task)
        allTask = TaskRepository.loadAllTask(context)
        assertThat(allTask).hasSize(1)
    }
}