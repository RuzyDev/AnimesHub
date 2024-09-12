package com.ruzy.animeshub.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class ResultState<out T> {
    data class Success<out T : Any?>(val data: T) : ResultState<T>()
    data class Failure(val exception: Throwable) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
}

inline fun <T : Any?> ResultState<T>.isLoading(crossinline action: (isLoading: Boolean) -> Unit): ResultState<T> {
    if (this is ResultState.Loading) action(true) else action(false)
    return this
}

inline fun <T : Any?> ResultState<T>.onSuccess(crossinline action: (T) -> Unit): ResultState<T> {
    if (this is ResultState.Success) action(this.data)
    return this
}

inline fun <T : Any?> ResultState<T>.onFailure(crossinline action: (exception: Throwable) -> Unit): ResultState<T> {
    if (this is ResultState.Failure) action(this.exception)
    return this
}

fun <T> Flow<T>.asResultState(): Flow<ResultState<T>> = this.map<T, ResultState<T>>{
    ResultState.Success(it)
}.onStart {
    emit(ResultState.Loading)
}.catch {
    it.printStackTrace()
    emit(ResultState.Failure(it))
}