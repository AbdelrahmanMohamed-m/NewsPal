package com.thechance.newspal.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.data.util.AuthenticationException
import com.thechance.data.util.ErrorHandler
import com.thechance.data.util.GeneralException
import com.thechance.data.util.NetworkException
import com.thechance.data.util.handelAuthenticationException
import com.thechance.data.util.handelGeneralException
import com.thechance.data.util.handelNetworkException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

abstract class BaseViewModel<T, E>(initialState: T) : ViewModel() {

    abstract val TAG: String
    protected open fun log(message: String) {
        Log.e(TAG, message)
    }

    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected val _effect = MutableSharedFlow<E>()
    val effect = _effect.asSharedFlow()


    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (t: ErrorHandler) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = function()
                log("tryToExecute:$result ")
                onSuccess(result)
            } catch (exception: GeneralException) {
                handelGeneralException(exception, onError)
                log("tryToExecute error GeneralException: ${exception}")
            } catch (exception: NetworkException) {
                handelNetworkException(exception, onError)
                log("tryToExecute error NetworkException: ${exception}")
            } catch (exception: AuthenticationException) {
                handelAuthenticationException(exception, onError)
                log("tryToExecute error AuthenticationException: ${exception}")
            } catch (exception: IOException) {
                log("tryToExecute error IOException: ${exception}")
                onError(ErrorHandler.NoConnection)
            } catch (exception: Exception) {
                log("tryToExecute error Exception: ${exception}")
                onError(ErrorHandler.UnKnownError)
            }
        }
    }
    protected fun <T : BaseUiEffect> effectActionExecutor(
        _effect: MutableSharedFlow<T>,
        effect: T,
    ) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

}
