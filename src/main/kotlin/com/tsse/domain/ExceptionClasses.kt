package com.tsse.domain

import com.tsse.domain.model.Exercise
import com.tsse.domain.model.Workout
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Exception classes.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@ResponseStatus(HttpStatus.CONFLICT, reason = "Resource already exists.")
abstract class ResourceAlreadyExistsException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.NOT_FOUND, reason = "Resource not found.")
abstract class ResourceNotFoundException(message: String) : RuntimeException(message)

class ExerciseAlreadyExistsException(exercise: Exercise) : ResourceAlreadyExistsException("$exercise already exists.")

class ExerciseNotFoundException : ResourceNotFoundException {

    constructor(id: Long) : super("Exercise with id \'$id\' not found.")

    constructor(name: String) : super("Exercise with name \'$name\' not found.")
}

class DataIntegrityException(errorMessages: List<String>) : RuntimeException(errorMessages.toString())

class ScheduleNotFoundException : ResourceNotFoundException {
    constructor(id: Long) : super("Schedule with id \'$id\' not found.")
}

class UserNotFoundException: ResourceNotFoundException {
    constructor(id: Long) : super("User with id \'$id\' not found.")
}

class WorkoutNotFoundException : ResourceNotFoundException {
    constructor(id: Long) : super("Workout with id \'$id\' not found.")
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class invalidFormException(message: String) : RuntimeException("Object sent is not valid: $message")