package com.thechance.data.util

open class GeneralException : Exception()
class InvalidDataException : GeneralException()
class NotFoundException : GeneralException()
class AlreadyExistException : GeneralException()


open class NetworkException : Exception()
class NoConnectionException : NetworkException()
class InternalServerException : NetworkException()


open class AuthenticationException : Exception()
class UnAuthorizedException : AuthenticationException()
class EmailIsExistException : AuthenticationException()
class ForbiddenException : AuthenticationException()


sealed interface ErrorHandler {
    object EmailIsExist : ErrorHandler
    object NoConnection : ErrorHandler
    object ServerError : ErrorHandler
    object InvalidData : ErrorHandler
    object NotFound : ErrorHandler
    object UnAuthorizedUser : ErrorHandler
    object AlreadyExist : ErrorHandler
    object UnKnownError : ErrorHandler

}


fun handelNetworkException(
    exception: NetworkException, onError: (ErrorHandler) -> Unit,
) {
    when (exception) {
        is InternalServerException -> onError(ErrorHandler.ServerError)

        is NoConnectionException -> onError(ErrorHandler.NoConnection)
    }
}

fun handelAuthenticationException(
    exception: AuthenticationException,
    onError: (t: ErrorHandler) -> Unit,
) {
    when (exception) {
        is EmailIsExistException -> onError(ErrorHandler.EmailIsExist)

        is ForbiddenException -> onError(ErrorHandler.UnAuthorizedUser)

        is UnAuthorizedException -> onError(ErrorHandler.UnAuthorizedUser)
    }
}

fun handelGeneralException(
    exception: GeneralException,
    onError: (t: ErrorHandler) -> Unit,
) {
    when (exception) {
        is InvalidDataException -> onError(ErrorHandler.InvalidData)

        is NotFoundException -> onError(ErrorHandler.NotFound)

        is AlreadyExistException -> onError(ErrorHandler.AlreadyExist)
    }
}
