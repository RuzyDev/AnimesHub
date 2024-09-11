package com.ruzy.animeshub.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.ruzy.animeshub.util.datastore.createDataStore
import com.ruzy.animeshub.util.datastore.dataStoreFileName


fun dataStore(): DataStore<Preferences> = createDataStore(
    producePath = {
//        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
//            directory = NSDocumentDirectory,
//            inDomain = NSUserDomainMask,
//            appropriateForURL = null,
//            create = false,
//            error = null,
//        )
//        requireNotNull(documentDirectory).path +
                "/$dataStoreFileName"
    }
)