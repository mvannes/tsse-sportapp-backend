package com.tsse.domain

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Exception classes.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@ResponseStatus(HttpStatus.CONFLICT, reason = "Resource already exists.")
class ResourceAlreadyExistsException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.NOT_FOUND, reason = "Resource not found.")
class ResourceNotFoundException(id: Long) : RuntimeException("Resource not found for id: $id")

class DataIntegrityException(errorMessages: List<String>) : RuntimeException(errorMessages.toString())
