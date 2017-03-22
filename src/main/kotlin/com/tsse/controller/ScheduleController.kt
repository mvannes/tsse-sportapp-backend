package com.tsse.controller

import com.tsse.model.Schedule
import com.tsse.repository.ScheduleRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller used for REST operations related to schedules.
 */
@RestController
@RequestMapping("/api/schedule")
class ScheduleController(val repository: ScheduleRepository) {


    @PostMapping
    fun saveSchedule(@RequestBody schedule: Schedule): ResponseEntity<Unit> {
        repository.save(schedule)

        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getSchedule(@PathVariable id: Long): ResponseEntity<Schedule> {
        val schedule = repository.findOne(id)

        return ResponseEntity.ok(schedule)
    }

    @GetMapping
    fun getSchedules(): ResponseEntity<List<Schedule>> {
        val schedules = repository.findAll().toList()

        return ResponseEntity.ok(schedules)
    }

    @PutMapping
    fun updateSchedule(@RequestBody schedule: Schedule): ResponseEntity<Unit> {
        repository.save(schedule)

        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteSchedule(@PathVariable id: Long): ResponseEntity<Unit> {
        repository.delete(id)

        return ResponseEntity(HttpStatus.OK)
    }


}
