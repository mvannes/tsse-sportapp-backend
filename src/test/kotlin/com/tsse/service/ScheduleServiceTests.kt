package com.tsse.service

import com.tsse.domain.ScheduleNotFoundException
import com.tsse.domain.model.Schedule
import com.tsse.repository.ScheduleRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Unit tests related to the ScheduleService.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@RunWith(SpringRunner::class)
class ScheduleServiceTests {

    @Mock lateinit var repository: ScheduleRepository

    lateinit var service: ScheduleService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(ScheduleServiceTests::class)
        service = ScheduleServiceImpl(repository)
    }

    @Test
    fun testSaveSchedule_returnsCreatedSchedule() {
        val expected = Schedule("Name", "Description", ArrayList(), 1)

        given(repository.save(expected)).willReturn(expected)

        val actual = service.saveSchedule(expected)

        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(repository, times(1)).save(expected)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testGetSchedule_returnsSchedule() {
        val expected = Schedule("Name", "Description", ArrayList(), 1)
        val id = 1L

        given(repository.findOne(id)).willReturn(expected)

        val actual = service.getSchedule(id)

        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(repository, times(1)).findOne(id)
        verifyNoMoreInteractions(repository)
    }

    @Test(expected = ScheduleNotFoundException::class)
    fun testGetSchedule_returnsScheduleNotFoundException() {

        val id = 1L

        given(repository.findOne(id)).willReturn(null)

        service.getSchedule(id)
    }

    @Test
    fun testGetAllSchedules_returnsScheduleList() {
        val expected = arrayListOf(
                Schedule("Name1", "Description1", ArrayList(), 1),
                Schedule("Name2", "Description2", ArrayList(), 2)
        )

        given(repository.findAll()).willReturn(expected)

        val actual = service.getAllSchedules()

        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(repository, times(1)).findAll()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testUpdateSchedule_returnsUpdatedSchedule() {
        val expected = Schedule("Name", "Description", ArrayList(), 1)
        val id = expected.id

        given(repository.findOne(id)).willReturn(expected)
        given(repository.save(expected)).willReturn(expected)

        val actual = service.updateSchedule(expected)

        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(repository, times(1)).findOne(id)
        verify(repository, times(1)).save(expected)
        verifyNoMoreInteractions(repository)
    }

    @Test(expected = ScheduleNotFoundException::class)
    fun testUpdateSchedule_returnsScheduleNotFoundException() {

        val schedule = Schedule("Name", "Description", ArrayList(), 1)
        val id = schedule.id

        given(repository.findOne(id)).willReturn(null)

        service.updateSchedule(schedule)
    }

    @Test
    fun testDeleteSchedule() {
        val id = 1L

        willDoNothing().given(repository).delete(id)

        service.deleteSchedule(id)

        verify(repository, times(1)).delete(id)
        verifyNoMoreInteractions(repository)
    }
}