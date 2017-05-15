package com.tsse.controller

import com.tsse.domain.model.Schedule
import com.tsse.service.ScheduleService
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Controller used for REST operations related to schedules.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/schedule")
class ScheduleController(private val service: ScheduleService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createSchedule(@Valid @RequestBody schedule: Schedule, errors: Errors) = service.saveSchedule(schedule)

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getSchedule(@PathVariable id: Long): Schedule = service.getSchedule(id)

    @GetMapping
    fun getSchedules(): List<Schedule> = service.getAllSchedules()

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateSchedule(@Valid @RequestBody schedule: Schedule, errors: Errors) = service.updateSchedule(schedule)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSchedule(@PathVariable id: Long) = service.deleteSchedule(id)


}
