package com.tsse.controller

import com.tsse.model.Exercise
import com.tsse.repository.ExerciseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
class ExerciseController(@Autowired val repository: ExerciseRepository) {

    @PostMapping
    fun saveExercise(@RequestBody exercise: Exercise): ResponseEntity<Unit> {
        repository.save(exercise)

        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping
    fun getExercises(): ResponseEntity<MutableIterable<Exercise>>? {
        val exercises = repository.findAll()

        return ResponseEntity.ok(exercises)
    }

    @GetMapping("/{id}")
    fun getExercise(@PathVariable id: Long): ResponseEntity<Exercise> {
        val exercise = repository.findOne(id)

        return ResponseEntity.ok(exercise)
    }

    @PutMapping
    fun updateExercise(@RequestBody exercise: Exercise): ResponseEntity<Exercise> {

        return ResponseEntity.ok(repository.save(exercise))
    }

    @DeleteMapping
    fun deleteExercises() = ResponseEntity(repository.deleteAll(), HttpStatus.NO_CONTENT)

    @DeleteMapping("/{id}")
    fun deleteExercise(@PathVariable id: Long) = ResponseEntity(repository.delete(id), HttpStatus.NO_CONTENT)

}