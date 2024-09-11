package com.ruzy.animeshub.util.log

expect interface CommonLogger {
    open fun log(tag: String, message: String)
}