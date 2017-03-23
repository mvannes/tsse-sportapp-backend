package com.tsse.controller

import com.tsse.ResponseBody
import com.tsse.model.Schedule
import com.tsse.repository.ScheduleRepository
import com.tsse.service.ScheduleServiceImpl
import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid

/**
 * Controller used for REST operations related to schedules.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/schedule")
class ScheduleController(val repository: ScheduleRepository, val service: ScheduleServiceImpl) {

    @PostMapping
    fun saveSchedule(@Valid @RequestBody schedule: Schedule, errors: Errors): ResponseEntity<ResponseBody<Unit>> {
        val result: ResponseBody<Unit> = ResponseBody()

        if (errors.hasErrors()) {
            result.msg = errors.allErrors.joinToString(", ")
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
    fun getSchedule(@PathVariable id: Long): ResponseEntity<ResponseBody<Schedule>> {

        val result: ResponseBody<Schedule> = ResponseBody()

        val schedule: Schedule
        try {
            schedule = service.getSchedule(id)
            result.response = schedule
        } catch (ex: NotFoundException) {
            result.msg = "Could not find schedule with id " + id
            return ResponseEntity.badRequest().body(result)
        }

        result.msg = "Found schedule."
        return ResponseEntity.ok(result)
    }

    @GetMapping
    fun getSchedules(): ResponseEntity<ResponseBody<List<Schedule>>> {

        val result: ResponseBody<List<Schedule>> = ResponseBody()

        result.msg = "Schedule list found!"
        result.response = service.getAllSchedules()

        return ResponseEntity.ok(result)
    }

    @PutMapping
    fun updateSchedule(@Valid @RequestBody schedule: Schedule): ResponseEntity<ResponseBody<Unit>> {

        repository.save(schedule)

        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteSchedule(@PathVariable id: Long): ResponseEntity<Unit> {
        repository.delete(id)

        return ResponseEntity(HttpStatus.OK)
    }


}
