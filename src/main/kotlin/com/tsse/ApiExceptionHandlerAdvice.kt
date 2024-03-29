package com.tsse

import com.tsse.domain.ApiError
import com.tsse.domain.DataIntegrityException
import com.tsse.domain.ResourceAlreadyExistsException
import com.tsse.domain.ResourceNotFoundException
import org.apache.commons.lang3.exception.ExceptionUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.TransactionSystemException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import javax.validation.ConstraintViolationException

/**
 * Global exception handler for handling exceptions thrown by rest controller methods.
 *
 * @author Boyd Hogerheijde
 * @author Fabian de Almeida Ramos
 * @version 1.1.0
 */
@ControllerAdvice(basePackages = arrayOf("com.tsse.controller"), annotations = arrayOf(RestController::class))
class ApiExceptionHandlerAdvice : ResponseEntityExceptionHandler() {

    private val log: Logger = LoggerFactory.getLogger(ApiExceptionHandlerAdvice::class.java)

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(exception: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        log.error("Not found exception caught: $exception")

        return handleException(exception, request, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ResourceAlreadyExistsException::class)
    fun handleConflict(exception: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        log.error("Conflicting exception caught: $exception")

        return handleException(exception, request, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityException(exception: DataIntegrityViolationException, request: WebRequest): ResponseEntity<Any> {
        log.error("Data integrity exception caught: $exception")

        return handleException(exception, request, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleInvalidForm(exception: ConstraintViolationException, request: WebRequest): ResponseEntity<Any> {
        val errorMessages = exception.constraintViolations.map { it.message }

        return handleException(DataIntegrityException(errorMessages), request, HttpStatus.BAD_REQUEST)
    }

    //As we're not creating any transactions ourselves, these exceptions only occur because of ConstraintViolationExceptions.
    @ExceptionHandler(TransactionSystemException::class)
    fun handleTransactionSystemException(exception: TransactionSystemException, request: WebRequest): ResponseEntity<Any> {
        val foundException = exception.rootCause as ConstraintViolationException

        return handleInvalidForm(foundException, request)
    }

    private fun handleException(exception: Exception, request: WebRequest, httpStatus: HttpStatus): ResponseEntity<Any> {
        val errorResponse = createResponse(exception, request)

        return handleExceptionInternal(exception, errorResponse, HttpHeaders(), httpStatus, request)
    }

    private fun createResponse(exception: Exception, request: WebRequest): ApiError {
        val errorMessage = exception.message
        val uri = request.getDescription(false) // Get requested uri, boolean value indicates whether or not the port is included.
        val errorResponse = ApiError(uri, errorMessage, ExceptionUtils.getRootCauseMessage(exception), Date())

        log.error("Root cause: " + ExceptionUtils.getRootCause(exception))

        return errorResponse
    }
}