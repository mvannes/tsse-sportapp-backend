package com.tsse.service

import com.tsse.domain.Schedule
import com.tsse.domain.ScheduleNotFoundException
import com.tsse.repository.ScheduleRepository
import org.springframework.stereotype.Service

/**
 * Service used for schedules.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Service
class ScheduleServiceImpl(val repository: ScheduleRepository) : ScheduleService {

    override fun saveSchedule(schedule: Schedule): Schedule {
        return repository.save(schedule)
    }

    override fun getSchedule(id: Long): Schedule = repository.findOne(id) ?: throw ScheduleNotFoundException(id)

    override fun getAllSchedules(): List<Schedule> {

        return repository.findAll().toList()

    }

    override fun updateSchedule(schedule: Schedule): Schedule {
        getSchedule(schedule.id)
        return repository.save(schedule)
    }

}