package com.tsse.controller

import com.tsse.domain.Exercise
import com.tsse.service.ExerciseService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
class ExerciseController(private val exerciseService: ExerciseService) {

    private val log: Logger = LoggerFactory.getLogger(ExerciseController::class.java)

    /**
     * Controller method for creating a new exercise.
     * Method delegates further logic to the service layer.
     *
     * @param exercise Passed in exercise to be stored in the database.
     *
     * @return The newly created exercise.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createExercise(@Valid @RequestBody exercise: Exercise, errors: Errors): Exercise {
        validateRequest(errors)

        return exerciseService.createExercise(exercise)
    }

    /**
     * Controller method for getting all exercises from the database.
     *
     * @return A list of exercises.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllExercises(): List<Exercise> = exerciseService.getAllExercises()

    /**
     * Controller method for getting a particular exercise via its identifier.
     * Method delegates further logic(i.e. checking existence) to the service layer.
     *
     * @param id Identifier of an exercise.
     *
     * @return Exercise object retrieved via its identifier.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getExerciseById(@PathVariable id: Long): Exercise = exerciseService.getExerciseById(id)

    /**
     * Controller method used to update an existing exercise object.
     * This method delegates further logic(i.e. existence check) to the service layer.
     *
     * @param exercise Exercise to be updated. Contains new values for its attributes.
     *
     * @return The updated exercise.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateExercise(@Valid @RequestBody exercise: Exercise, errors: Errors): Exercise {
        validateRequest(errors)

        return exerciseService.updateExercise(exercise)
    }

    /**
     * Deletes all exercises.
     * Temporarily used in testing environments.
     *
     * This method is dangerous and will not be available in production environments.
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllExercises() = exerciseService.deleteAllExercises()

    /**
     * Deletes a particular exercise.
     *
     * @param id Identifier of the exercise to be deleted.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteExercise(@PathVariable id: Long) = exerciseService.deleteExercise(id)

    private fun validateRequest(errors: Errors) {
        if (errors.hasErrors()) {
            val errorMessages = errors.allErrors.map { it.defaultMessage }
            errorMessages.forEach { log.error(it) }

        }
    }

}