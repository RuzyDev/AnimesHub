package com.ruzy.animeshub.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.ruzy.animeshub.database.AnimesHubDatabase

actual class SqlDelightDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AnimesHubDatabase.Schema, "animeshub.db")
    }
}
