package com.tsse.service

import com.tsse.domain.ResourceNotFoundException
import com.tsse.domain.model.Schedule
import com.tsse.repository.ScheduleRepository
import com.tsse.service.impl.ScheduleService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.willDoNothing
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

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
        service = ScheduleService(repository)
    }

    @Test
    fun testSaveSchedule_returnsCreatedSchedule() {
        val expected = Schedule("Name", "Description", ArrayList(), 1)

        given(repository.save(expected)).willReturn(expected)

        val actual = service.create(expected)

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

        val actual = service.findOne(id)

        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(repository, times(1)).findOne(id)
        verifyNoMoreInteractions(repository)
    }

    @Test(expected = ResourceNotFoundException::class)
    fun testGetSchedule_returnsScheduleNotFoundException() {

        val id = 1L

        given(repository.findOne(id)).willReturn(null)

        service.findOne(id)
    }

    @Test
    fun testGetAllSchedules_returnsScheduleList() {
        val expected = arrayListOf(
                Schedule("Name1", "Description1", ArrayList(), 1),
                Schedule("Name2", "Description2", ArrayList(), 2)
        )

        given(repository.findAll()).willReturn(expected)

        val actual = service.findAll()

        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(repository, times(1)).findAll()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testUpdateSchedule_returnsUpdatedSchedule() {
        val mockSchedule = Schedule("Name", "Description", ArrayList(), 1)
        val id = mockSchedule.id

        given(repository.save(mockSchedule)).willReturn(mockSchedule)

        service.update(mockSchedule, id)

        verify(repository, times(1)).save(mockSchedule)
        verifyNoMoreInteractions(repository)
    }


    @Test
    fun testDeleteSchedule() {
        val id = 1L

        willDoNothing().given(repository).delete(id)

        service.delete(id)

        verify(repository, times(1)).delete(id)
        verifyNoMoreInteractions(repository)
    }
}