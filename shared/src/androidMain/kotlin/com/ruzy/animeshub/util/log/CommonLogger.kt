package com.ruzy.animeshub.util.log

actual interface CommonLogger {
    actual fun log(tag:String, message:String){
//        if(BuildConfig.DEBUG){
//            Log.d(BuildConfig.BUILD_TYPE, message)
//        }
    }
}