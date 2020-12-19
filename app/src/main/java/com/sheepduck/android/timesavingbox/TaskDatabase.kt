package com.sheepduck.android.timesavingbox

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Task::class), version = 2)
abstract class TaskDatabase : RoomDatabase() {
    //クラス内のprivate定数を宣言するためにcompanion objectブロックとする。
    companion object {
        //データベースファイル名の定数フィールド
        private const val DATABASE_NAME = "timesavingbox.db"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                CREATE TABLE new_timesavingbox (
                    _id INTEGER NOT NULL,
                    date TEXT NOT NULL,
                    starttime TEXT,
                    endtime TEXT,
                    memo TEXT,
                    PRIMARY KEY (_id, date)
                )
                """.trimIndent())
                database.execSQL("""
                INSERT INTO new_timesavingbox (_id, date, starttime, endtime, memo)
                SELECT _id, date, starttime, endtime, memo FROM timesavingbox
                """.trimIndent())
                database.execSQL("DROP TABLE timesavingbox")
                database.execSQL("ALTER TABLE new_timesavingbox RENAME TO timesavingbox")
            }
        }

        fun createInstance(context: Context): TaskDatabase {
            // ToDo: マイグレーションの実装。
            return Room.databaseBuilder(context, TaskDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2, MIGRATION_1_2).build()
        }
    }

    abstract fun taskDao(): TaskDao
}