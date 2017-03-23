package com.tsse.controller

import com.tsse.ResponseBody
import com.tsse.model.Schedule
import com.tsse.repository.ScheduleRepository
import com.tsse.service.ScheduleServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid

/**
 * Controller used for REST operations related to schedules.
 */
@RestController
@RequestMapping("/api/schedule")
class ScheduleController(val repository: ScheduleRepository, val service: ScheduleServiceImpl) {

    @PostMapping
    fun saveSchedule(@Valid @RequestBody schedule: Schedule, errors: Errors): ResponseEntity<ResponseBody<Unit>> {

        val result: ResponseBody<Unit> = ResponseBody()

        if (errors.hasErrors()) {
            result.msg = errors.fieldErrors.joinToString(", ")
            return ResponseEntity.badRequest().body(result)
        }
        if (service.saveSchedule(schedule)) {
            result.msg = "Schedule created!"

            val location: URI = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(schedule.id).toUri()

            return ResponseEntity.created(location).body(result)
        }
        result.msg = "Could not save schedule"
        return ResponseEntity.badRequest().body(result)
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
