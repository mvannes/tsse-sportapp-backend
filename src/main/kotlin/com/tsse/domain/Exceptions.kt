package com.tsse.domain

import com.tsse.domain.model.Exercise
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Exception classes.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Exercise already exists.")
class ExerciseAlreadyExistsException(exercise: Exercise) : Throwable("Exercise with name \'${exercise.name}\' already exists!")

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such exercise.")
class ExerciseNotFoundException(id: Long) : Throwable("Exercise with id \'$id\' does not exist!")