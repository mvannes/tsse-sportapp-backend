package com.tsse

import com.tsse.domain.ResourceAlreadyExistsException
import com.tsse.domain.ResourceNotFoundException
import com.tsse.domain.ApiErrorResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

/**
 * Global exception handler for handling exceptions thrown by rest controller methods.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@ControllerAdvice(basePackages = arrayOf("com.tsse.controller"), annotations = arrayOf(RestController::class))
class GlobalApiExceptionHandlerAdvice : ResponseEntityExceptionHandler() {

    private val log: Logger = LoggerFactory.getLogger(GlobalApiExceptionHandlerAdvice::class.java)

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(exception: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        log.error("Resource not found exception caught: $exception")

        val errorResponse = createResponse(exception, request)

        return handleExceptionInternal(exception, errorResponse, HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler(ResourceAlreadyExistsException::class)
    fun handleConflict(exception: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        log.error("Resource already exists exception caught: $exception")

        val errorResponse = createResponse(exception, request)

        return handleExceptionInternal(exception, errorResponse, HttpHeaders(), HttpStatus.CONFLICT, request)
    }

    private fun createResponse(exception: RuntimeException, request: WebRequest): ApiErrorResponse {
        val errorMessage = exception.message
        val uri = request.getDescription(false) // Get requested uri, boolean value indicates whether or not the port is included.
        val errorResponse = ApiErrorResponse(uri, errorMessage, Date())

        return errorResponse
    }
}