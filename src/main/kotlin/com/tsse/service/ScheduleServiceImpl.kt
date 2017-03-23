package com.tsse.service

import com.tsse.model.Schedule
import com.tsse.repository.ScheduleRepository
import org.springframework.stereotype.Service

/**
 * Created by fabian on 22/03/2017.
 */
@Service
class ScheduleServiceImpl(val repository: ScheduleRepository) {

    fun saveSchedule(schedule: Schedule): Boolean {
        repository.save(schedule)

        return true
    }

}