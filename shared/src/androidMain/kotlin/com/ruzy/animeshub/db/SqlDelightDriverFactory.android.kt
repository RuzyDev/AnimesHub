package com.ruzy.animeshub.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ruzy.animeshub.database.AnimesHubDatabase

actual class SqlDelightDriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AnimesHubDatabase.Schema, context, "animeshub.db")
    }
}
