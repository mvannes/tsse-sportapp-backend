package com.tsse.controller

import com.tsse.domain.Workout
import com.tsse.domain.invalidFormException
import com.tsse.service.WorkoutService
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Controller used for REST operations related to Workouts.
 *
 * @author Floris van Lent
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/schedule")
class WorkoutController(private val service: WorkoutService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createWorkout(@Valid @RequestBody schedule: Workout, errors: Errors): Workout {
        validateRequest(errors)

        return service.saveWorkout(schedule)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getWorkout(@PathVariable id: Long): Workout = service.getWorkout(id)

    @GetMapping
    fun getWorkouts(): List<Workout> = service.getAllWorkouts()

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateWorkout(@Valid @RequestBody schedule: Workout, errors: Errors): Workout {
        validateRequest(errors)

        return service.updateWorkout(schedule)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteWorkout(@PathVariable id: Long) = service.deleteWorkout(id)

    private fun validateRequest(errors: Errors) {
        if (errors.hasErrors()) {

            val errorMessages = errors.allErrors.map { it.defaultMessage }.toString()
            throw invalidFormException(errorMessages)

        }
    }


}