package com.tsse.controller

import com.tsse.domain.model.Exercise
import com.tsse.domain.ExerciseAlreadyExistsException
import com.tsse.domain.ExerciseNotFoundException
import com.tsse.service.ExerciseServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * REST controller for accessing exercise data.
 * All CRUD functionalities are supported.
 *
 * @author Boyd Hogerheijde
 * @author Fabian de Almeida Ramos
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/exercises")
class ExerciseController(private val exerciseService: ExerciseServiceImpl) {

    private val log: Logger = LoggerFactory.getLogger(ExerciseController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createExercise(@RequestBody exercise: Exercise): Exercise = exerciseService.createExercise(exercise)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllExercises(): List<Exercise> = exerciseService.getAllExercises()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getExerciseById(@PathVariable id: Long): Exercise = exerciseService.getExerciseById(id)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateExercise(@RequestBody exercise: Exercise): Exercise = exerciseService.updateExercise(exercise)

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllExercises() = exerciseService.deleteAllExercises()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteExercise(@PathVariable id: Long) = exerciseService.deleteExercise(id)

    @ExceptionHandler(ExerciseAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleExerciseAlreadyExistsException() {
        log.info("Exercise already exists exception caught!")
    }

    @ExceptionHandler(ExerciseNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleExerciseNotFoundException() {
        log.info("Exercise not found exception caught!")
    }
}
