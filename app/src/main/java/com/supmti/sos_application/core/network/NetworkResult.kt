package com.supmti.sos_application.core.network

/** Shared result shape for a future API client; no network client is configured in this phase. */
sealed interface NetworkResult<out T> {
    data class Success<T>(val value: T) : NetworkResult<T>
    data class Failure(val message: String, val cause: Throwable? = null) : NetworkResult<Nothing>
}
