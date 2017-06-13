package com.tsse.controller

import com.tsse.domain.model.Schedule
import com.tsse.service.impl.ScheduleService
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
@RequestMapping("/api/schedules")
class ScheduleController(private val service: ScheduleService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createSchedule(@Valid @RequestBody schedule: Schedule, errors: Errors) = service.create(schedule)

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getSchedule(@PathVariable id: Long): Schedule = service.findOne(id)

    @GetMapping
    fun getSchedules(): List<Schedule> = service.findAll()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateSchedule(@Valid @RequestBody schedule: Schedule, @PathVariable id: Long, errors: Errors) = service.update(schedule, id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSchedule(@PathVariable id: Long) = service.delete(id)


}
