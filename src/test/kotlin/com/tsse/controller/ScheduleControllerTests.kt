package com.tsse.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tsse.domain.Schedule
import com.tsse.domain.ScheduleNotFoundException
import com.tsse.service.ScheduleService
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.willDoNothing
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

/**
 * Tests related to the ScheduleController.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */

@RunWith(SpringRunner::class)
class ScheduleControllerTests {

    @Mock lateinit var service: ScheduleService

    @InjectMocks lateinit var scheduleController: ScheduleController

    lateinit var mockMvc: MockMvc

    val URI = "/api/schedule/"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this::class)
        mockMvc = MockMvcBuilders
                .standaloneSetup(scheduleController)
                .build()
    }

    @Test
    fun testSaveSchedule_returnsHttpStatusIsCreated() {

        val schedule = Schedule("Name", "Description", ArrayList(), 1)

        given(service.saveSchedule(schedule)).willReturn(schedule)
        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isCreated)
                .andExpect { content().equals(schedule) }
    }

    @Test
    fun testSaveScheduleEmptyName_returnsHttpStatusIsBadRequest() {

        val schedule = Schedule("", "Description", ArrayList(), 1)

        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isBadRequest)
                .andExpect { jsonPath("message", equalTo("Object sent is not valid: [Schedule name cannot be empty!]")) }
    }

    @Test
    fun testSaveScheduleEmptyDescription_returnsHttpStatusIsCreated() {

        val schedule = Schedule("Name", "", ArrayList(), 1)

        given(service.saveSchedule(schedule)).willReturn(schedule)
        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isCreated)
                .andExpect { content().equals(schedule) }
    }

    @Test
    fun testSaveScheduleEmptyAmountOfTrainings_returnsHttpStatusIsBadRequest() {

        val schedule = Schedule("Name", "Description", ArrayList(), 0)

        given(service.saveSchedule(schedule)).willReturn(schedule)
        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isBadRequest)
                .andExpect { jsonPath("message", equalTo("Object sent is not valid: [Schedule needs at least one training per week!]")) }
    }

    @Test
    fun testGetScheduleById_returnsHttpStatusOk() {

        val schedule = Schedule("Name", "Description", ArrayList(), 0)
        val id = 1L
        given(service.getSchedule(id)).willReturn(schedule)

        mockMvc.perform(
                get(URI + "{id}", id))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect { content().equals(schedule) }


    }

    @Test
    fun testGetScheduleById_returnsHttpStatusIsNotFound() {

        val id = 1L
        given(service.getSchedule(id)).willThrow(ScheduleNotFoundException::class.java)

        mockMvc.perform(
                get(URI + "{id}", id))
                .andExpect(status().isNotFound)
    }

    @Test
    fun testGetAllSchedules_returnsHttpStatusOk() {

        val results = arrayListOf(Schedule("Name", "Description", ArrayList(), 0),
                Schedule("Name2", "Description2", ArrayList(), 0))


        given(service.getAllSchedules()).willReturn(results)

        mockMvc.perform(
                get(URI))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect { content().equals(results) }

    }

    @Test
    fun testUpdateSchedule_returnsHttpStatusOk() {
        val schedule = Schedule("Name", "Description", ArrayList(), 1)

        given(service.updateSchedule(schedule)).willReturn(schedule)

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isOk)
                .andExpect { content().equals(schedule) }

    }


    @Test
    fun testUpdateScheduleInvalidForm_returnsHttpStatusBadRequest() {

        val schedule = Schedule("", "Description", ArrayList(), 1)

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isBadRequest)
                .andExpect { jsonPath("message", equalTo("Object sent is not valid: [Schedule name cannot be empty!]")) }

    }

    @Test
    fun testUpdateScheduleNonExistingSchedule_returnsHttpStatusNotFound() {

        val schedule = Schedule("Name", "Description", ArrayList(), 1)

        given(service.updateSchedule(schedule)).willThrow(ScheduleNotFoundException::class.java)

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(schedule)))
                .andExpect(status().isNotFound)

    }

    @Test
    fun testDeleteSchedule_returnsHttpStatusOK() {

        val id = 1L

        willDoNothing().given(service).deleteSchedule(id)

        mockMvc.perform(
                delete(URI + "{id}", id))
                .andExpect (status().isOk)

    }

    private fun asJsonString(obj: Any): String = ObjectMapper().writeValueAsString(obj)


}
